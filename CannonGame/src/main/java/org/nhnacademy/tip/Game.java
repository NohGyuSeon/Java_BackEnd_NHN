package org.nhnacademy.tip;

import java.awt.Color;
import javax.swing.*;

public class Game extends JFrame {
    final CannonWorld world;
    JTextField powerField;
    JTextField angleField;

    public Game(int width, int height) {
        super();

        setSize(width, height);
        setLayout(null);

        world = new CannonWorld(getWidth() - 100, getHeight() - 200);
        world.setLocation(50, 60);

        add(world);

        world.setInterval(20);
        world.addEffect(new Vector(2, -90));

        Box hazard = new Box(new Point(world.getWidth() / 2, 10), world.getWidth(), 20, Color.DARK_GRAY);
        world.addHazard(hazard);

        JSlider powerSlider = new JSlider();
        powerSlider.setOrientation(SwingConstants.HORIZONTAL);
        powerSlider.setBounds(world.getX(), world.getY() + world.getHeight() + 10, world.getWidth(),40);
        powerSlider.setMinimum(1);
        powerSlider.setMaximum(100);
        powerSlider.setValue(world.getMagnitude());
        powerSlider.addChangeListener(event-> {
            world.setMagnitude(powerSlider.getValue());
            powerField.setText(String.valueOf(world.getMagnitude()));
        });
        add(powerSlider);


        JSlider angleSlider = new JSlider();
        angleSlider.setOrientation(SwingConstants.VERTICAL);
        angleSlider.setBounds(world.getX() + world.getWidth() + 10, world.getY(), 50, world.getHeight());
        angleSlider.setMinimum(0);
        angleSlider.setMaximum(90);
        angleSlider.setValue(world.getAngle());
        angleSlider.addChangeListener(event-> {
            world.setAngle(angleSlider.getValue());
            angleField.setText(String.valueOf(world.getAngle()));
        });
        add(angleSlider);

        JButton fireButton = new JButton();
        fireButton.setText("Fire");
        fireButton.setBounds(world.getX() + 50,
                powerSlider.getY() + powerSlider.getHeight() + 10, 90, 40);
        fireButton.addActionListener(event-> world.fire());
        add(fireButton);

        JButton clearButton = new JButton();
        clearButton.setText("Clear");
        clearButton.setBounds(fireButton.getX() + fireButton.getWidth() + 10,
                powerSlider.getY() + powerSlider.getHeight() + 10, 90, 40);
        clearButton.addActionListener(event-> world.clear());
        add(clearButton);

        JButton quitButton = new JButton();
        quitButton.setText("Quit");
        quitButton.setBounds(clearButton.getX() + clearButton.getWidth() + 10,
                powerSlider.getY() + powerSlider.getHeight() + 10, 90, 40);
        quitButton.addActionListener(event-> { close(); dispose(); } );
        add(quitButton);

        JLabel powerLabel = new JLabel();
        powerLabel.setBounds(world.getX(), world.getY() - 50, 90, 40);
        powerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        powerLabel.setText("Power : ");
        add(powerLabel);

        powerField = new JTextField();
        powerField.setBounds(powerLabel.getX() + powerLabel.getWidth() + 10, powerLabel.getY() , 90, 40);
        powerField.setText(String.valueOf(world.getMagnitude()));
        add(powerField);

        JLabel angleLabel = new JLabel();
        angleLabel.setBounds(powerField.getX() + powerField.getWidth() + 10, powerField.getY() , 90, 40);
        angleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        angleLabel.setText("Angle : ");
        add(angleLabel);

        angleField = new JTextField();
        angleField.setBounds(angleLabel.getX() + angleLabel.getWidth() + 10, angleLabel.getY(),
                powerField.getWidth(), powerField.getHeight());
        angleField.setText(String.valueOf(world.getAngle()));
        add(angleField);

    }


    public void run(long seconds) {
        Box box2 = new Box(new Point(world.getWidth() - 20, 20), 40, 40, Color.BLUE);
        world.setTarget(box2);

        world.run(seconds);
    }

    public void close() {
        Thread.currentThread().interrupt();
    }
}
