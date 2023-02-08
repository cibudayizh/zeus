package com.zeus.system.banner;


import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.CodeSource;
import java.util.jar.Attributes;
import java.util.jar.JarFile;

/**
 * @author zhuzihang
 * @create 2021/12/29
 */
public final class ZeusVersion {

    private ZeusVersion() {
    }

    /**
     * Return the full version string of the present Spring Boot codebase, or {@code null}
     * if it cannot be determined.
     * @return the version of Spring Boot or {@code null}
     * @see Package#getImplementationVersion()
     */
    public static String getVersion() {
        return determineSpringBootVersion();
    }

    private static String determineSpringBootVersion() {
        String implementationVersion = ZeusVersion.class.getPackage().getImplementationVersion();
        if (implementationVersion != null) {
            return implementationVersion;
        }
        CodeSource codeSource = ZeusVersion.class.getProtectionDomain().getCodeSource();
        if (codeSource == null) {
            return null;
        }
        URL codeSourceLocation = codeSource.getLocation();
        try {
            URLConnection connection = codeSourceLocation.openConnection();
            if (connection instanceof JarURLConnection) {
                return getImplementationVersion(((JarURLConnection) connection).getJarFile());
            }
            try (JarFile jarFile = new JarFile(new File(codeSourceLocation.toURI()))) {
                return getImplementationVersion(jarFile);
            }
        }
        catch (Exception ex) {
            return null;
        }
    }

    private static String getImplementationVersion(JarFile jarFile) throws IOException {
        return jarFile.getManifest().getMainAttributes().getValue(Attributes.Name.IMPLEMENTATION_VERSION);
    }
}
