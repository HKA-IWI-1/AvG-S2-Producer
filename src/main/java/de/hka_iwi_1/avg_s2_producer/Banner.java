package de.hka_iwi_1.avg_s2_producer;

import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.Objects;

/**
 * Banner as String-constant for the server start.
 */
final class Banner {

    // https://www.asciiart.eu/text-to-ascii-art
    private static final String FIGLET = """
                _         ____   ____ ____    ____                _                    \s
               / \\__   __/ ___| / ___|___ \\  |  _ \\ _ __ ___   __| |_   _  ___ ___ _ __\s
              / _ \\ \\ / / |  _  \\___ \\ __) | | |_) | '__/ _ \\ / _` | | | |/ __/ _ \\ '__|
             / ___ \\ V /| |_| |  ___) / __/  |  __/| | | (_) | (_| | |_| | (_|  __/ |  \s
            /_/   \\_\\_/  \\____| |____/_____| |_|   |_|  \\___/ \\__,_|\\__,_|\\___\\___|_|  \s
            """;
    private static final String JAVA = Runtime.version() + "-" + System.getProperty("java.vendor");
    private static final String OS_VERSION = System.getProperty("os.name");
    private static final InetAddress LOCALHOST = getLocalhost();
    private static final long MEGABYTE = 1024L * 1024L;
    private static final Runtime RUNTIME = Runtime.getRuntime();
    private static final String USERNAME = System.getProperty("user.name");

    static final String TEXT = """

            $figlet
            (C) Florian Sauer, Adrian Spindler, Luca Breisinger, Ronny Friedmann
            Version             1
            Spring Boot         $springBoot
            Spring Framework    $spring
            Java                $java
            Operating system    $os
            Computer name       $computerName
            IP-Address          $ip
            Heap: Size          $heapSize MiB
            Heap: Free          $heapFree MiB
            Username            $username
            JVM Locale          $locale
            """
            .replace("$figlet", FIGLET)
            .replace("$springBoot", SpringBootVersion.getVersion())
            .replace("$spring", Objects.requireNonNull(SpringVersion.getVersion()))
            .replace("$java", JAVA)
            .replace("$os", OS_VERSION)
            .replace("$computerName", LOCALHOST.getHostName())
            .replace("$ip", LOCALHOST.getHostAddress())
            .replace("$heapSize", String.valueOf(RUNTIME.totalMemory() / MEGABYTE))
            .replace("$heapFree", String.valueOf(RUNTIME.freeMemory() / MEGABYTE))
            .replace("$username", USERNAME)
            .replace("$locale", Locale.getDefault().toString());

    @SuppressWarnings("ImplicitCallToSuper")
    private Banner() {
    }

    private static InetAddress getLocalhost() {
        try {
            return InetAddress.getLocalHost();
        } catch (final UnknownHostException ex) {
            throw new IllegalStateException(ex);
        }
    }

}
