package com.inigo.domotik.thermostat.threads;

import com.pi4j.io.gpio.*;

public class Gpio {
    private static GpioPinDigitalOutput pin;
    public static void setAs(Pin rpin, boolean active) {
        final GpioController gpio = GpioFactory.getInstance();
        if (pin == null) {
            pin = gpio.provisionDigitalOutputPin(rpin);
        }
        if (active) {
            pin.high();
        } else {
            pin.low();
        }
        gpio.shutdown(); // Libera recursos
    }
}