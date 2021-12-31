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

public class IntegerInput extends TextField {
    DecimalFormat format = new DecimalFormat( "#" );

    public void addEventHandler(EventHandler<ActionEvent> eventHandler){
        setOnAction(eventHandler);
    }

    public IntegerInput(int initialValue){
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
    public void bindEvent(Function<Integer,Boolean> eventHandler){
        this.textProperty().addListener((observable,oldValue,newValue)->{
            if(!eventHandler.apply(Integer.parseInt(newValue)))
                textProperty().set(oldValue);
        });
    }
}
