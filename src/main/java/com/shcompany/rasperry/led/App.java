package com.shcompany.rasperry.led;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiBcmPin;
import com.pi4j.io.gpio.RaspiGpioProvider;
import com.pi4j.io.gpio.RaspiPinNumberingScheme;

public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {

    	//BCM Numbering
    	GpioFactory.setDefaultProvider(new RaspiGpioProvider(RaspiPinNumberingScheme.BROADCOM_PIN_NUMBERING));
    	
    	//Create gpio controller
        final GpioController gpio = GpioFactory.getInstance();

        //Provision gpio pin #05 as an output pin and turn off
        final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiBcmPin.GPIO_05, "MyLED", PinState.LOW);

        //On exit, forcefully shutdown all GPIO monitoring threads and scheduled tasks
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
            	gpio.shutdown();
            }
        }));        
        
        //Loops and toggle pin
    	while(true){    		
    		pin.toggle();
    		Thread.sleep(500);
    	}  
    }
}
