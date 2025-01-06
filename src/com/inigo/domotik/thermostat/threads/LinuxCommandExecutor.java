package com.inigo.domotik.thermostat.threads;

import java.io.IOException;

public class LinuxCommandExecutor {
    public static void execute(String comm) {
        String[] command = {"/bin/bash", "-c", comm};

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);

            processBuilder.environment().putAll(System.getenv());

            processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);

            Process process = processBuilder.start();

            int exitCode = process.waitFor();
            System.out.println("El proceso " + comm + "  terminó con código: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}