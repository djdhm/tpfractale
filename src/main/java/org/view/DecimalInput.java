package org.view;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import javax.swing.*;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.function.Function;

public class DecimalInput extends TextField {
    DecimalFormat format = new DecimalFormat( "#.0" );

    public void addEventHandler(EventHandler<ActionEvent> eventHandler){
        setOnAction(eventHandler);
    }

    public DecimalInput(double initialValue){
        super(initialValue+"");
        setMaxWidth(50);
        setTextFormatter( new TextFormatter<>(c ->
        {
            if ( c.getControlNewText().isEmpty() )
            {
                return c;
            }

            ParsePosition parsePosition = new ParsePosition( 0 );
            Object object = format.parse( c.getControlNewText(), parsePosition );

            if ( object == null || parsePosition.getIndex() < c.getControlNewText().length() )
            {
                return null;
            }
            else
            {
                return c;
            }
        }));

    }
    public void bindEvent(Function<Double,Boolean> eventHandler){
        this.textProperty().addListener((observable,oldValue,newValue)->{
                if(!eventHandler.apply(Double.parseDouble(newValue)))
                    textProperty().set(oldValue);
        });
    }
}
