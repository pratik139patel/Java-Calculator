package src;

import java.awt.Graphics;
import javax.swing.JButton;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.FontMetrics;

public class CustomJButton extends JButton
{
    private static final long serialVersionUID = 1L;
    private Color borderColor;
    private Color fontColor;
    private Color hoverBackgroundColor;
    private Color pressedBackgroundColor;
    private Color disabledBackgroundColor;
    private int arcWidth;
    private int arcHeight;
    private boolean invertFontColor;
    
    /**
     * Create a button without any text on it
     */
    public CustomJButton()
    {
        super();
        resetButtonSettings();
    }
    
    /**
     * Create a button with text on it
     * @param text String to be written on the button
     */
    public CustomJButton(String text)
    {
        super(text);
        resetButtonSettings();
    }
    
    /**
     * @param btn JButton is used to create a new CustomJButton
     */
    public CustomJButton(JButton btn)
    {
        super(btn.getText());
        resetButtonSettings();
    }

    /**
     * Initialize and reset all button settings
     */
    private void resetButtonSettings()
    {
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.resetBorderColor();
        this.resetFontColor();
        this.resetHoverBackgroundColor();
        this.resetPressedBackgroundColor();
        this.resetDisabledBackgroundColor();
        this.resetArcWidth();
        this.resetArcHeight();
        this.resetInvertedFontColorFlag();
    }

    /**
     * @param bool Set the invertFontColor flag to invert or not invert the font color relative to the background
     */
    public void setInvertedFontColorFlag(boolean bool) { this.invertFontColor = bool; }

    /**
     * Reset inverted font color to false to prevent complementary font color relative to the button bg color
     */
    public void resetInvertedFontColorFlag() { this.invertFontColor = false; }

    /**
     * @return If font colors are inverted based on button background, then returns true
     */
    public boolean getInvertedFontColorFlag() { return this.invertFontColor; }

    /**
     * @param borderColor Set the button border color
     */
    public void setBorderColor(Color borderColor) { this.borderColor = borderColor; }

    /**
     * Reset the button border color
     */
    public void resetBorderColor() { this.borderColor = Color.BLACK; }
    
    /**
     * @return The button border color exact object
     */
    public Color getBorderColor() { return this.borderColor; }

    /**
     * @param fontColor Set button text color
     */
    public void setFontColor(Color fontColor) { this.fontColor = fontColor; }

    /**
     * Reset button text color
     */
    public void resetFontColor() { this.fontColor = Color.BLACK; }

    /**
     * @return Button text color exact object
     */
    public Color getFontColor() { return this.fontColor; }

    /**
     * @param arcWidth Set width of the arc around the rounded button button corner
     */
    public void setArcWidth(int arcWidth) { this.arcWidth = arcWidth; }

    /**
     * Reset width of the arc around the rounded button button corner
     */
    public void resetArcWidth() { this.arcWidth = 20; }

    /**
     * @return Width of the arc around the rounded button button corner
     */
    public int getArcWidth() { return this.arcWidth; }

    /**
     * @param arcHeight Set height of the arc around the rounded button button corner
     */
    public void setArcHeight(int arcHeight) { this.arcHeight = arcHeight; }

    /**
     * Reset height of the arc around the rounded button button corner
     */
    public void resetArcHeight() { this.arcHeight = 20; }

    /**
     * @return Height of the arc around the rounded button button corner
     */
    public int getArcHeight() { return this.arcHeight; }
    
    /**
     * @return Color of the button when mouse is hovered over it
     */
    public Color getHoverBackgroundColor() { return this.hoverBackgroundColor; }

    /**
     * Reset color of the button when mouse is hovered over it
     */
    public void resetHoverBackgroundColor() { this.hoverBackgroundColor = Color.LIGHT_GRAY; }

    /**
     * @param hoverBackgroundColor Set color of the button when mouse is hovered over it
     */
    public void setHoverBackgroundColor(Color hoverBackgroundColor) { this.hoverBackgroundColor = hoverBackgroundColor; }

    /**
     * @return Color of the button when pressed
     */
    public Color getPressedBackgroundColor() { return this.pressedBackgroundColor; }

    /**
     * Reset color of the button when pressed
     */
    public void resetPressedBackgroundColor() { this.pressedBackgroundColor = Color.GRAY; }

    /**
     * @param pressedBackgroundColor Set color of the button when pressed
     */
    public void setPressedBackgroundColor(Color pressedBackgroundColor) { this.pressedBackgroundColor = pressedBackgroundColor; }

    /**
     * @param disabledBackgroundColor Set the background color of the button when disabled
     */
    public void setDisabledBackgroundColor(Color disabledBackgroundColor) { this.disabledBackgroundColor = disabledBackgroundColor; }
    
    /**
     * Reset the background color of the button when disabled
     */
    public void resetDisabledBackgroundColor() { this.disabledBackgroundColor = Color.DARK_GRAY; }
    
    /**
     * @return The background color of the button when disabled
     */
    public Color getDisabledBackgroundColor() { return this.disabledBackgroundColor; }

    /**
     * @return Gets the inverted font color which is complementary to the button bg color
     */
    public Color getInvertedFontColor(final Color bgColor)
    {
        if( (bgColor.getRed() >= 100 && bgColor.getRed() <= 155) && 
            (bgColor.getGreen() >= 100 && bgColor.getGreen() <= 155) && 
            (bgColor.getBlue() >= 100 && bgColor.getBlue() <= 155) ) 
            { return Color.WHITE; }
        else { return new Color(255-bgColor.getRed(), 255-bgColor.getGreen(), 255-bgColor.getBlue()); }
    }

    /**
     * @param bgColor BG color of the object
     * @param lowWhiteThreshold Low Threshold of the range which causes white color to be returned if all the RGB intensity lies within the range
     * @param highWhiteThreshold High Threshold of the range which causes white color to be returned if all the RGB intensity lies within the range
     * @param blackOrWhiteFlag Returns only black or white color. Black if it lies outside the range [low, high], otherwise white color is returned
     * @return White color if all RGB intensity lies between and including [low, high], otherwise returns complementary color to BG color if blackOrWhiteFlag is false or just white or black if blackOrWhiteFlag is true
     * @throws Exception Throws exception if lowWhiteThreshold < 0 or highWhiteThreshold > 255 or lowWhiteThreshold > highWhiteThreshold
     */
    public Color getInvertedColor(final Color bgColor, final int lowWhiteThreshold, final int highWhiteThreshold, final boolean blackOrWhiteFlag) throws Exception
    {
        if(lowWhiteThreshold < 0 || highWhiteThreshold > 255 || lowWhiteThreshold > highWhiteThreshold) { throw new Exception("ERROR: Threshold is [0,255] and low <= high"); }

        if( (bgColor.getRed() >= lowWhiteThreshold && bgColor.getRed() <= highWhiteThreshold) && 
            (bgColor.getGreen() >= lowWhiteThreshold && bgColor.getGreen() <= highWhiteThreshold) && 
            (bgColor.getBlue() >= lowWhiteThreshold && bgColor.getBlue() <= highWhiteThreshold) ) 
            { return Color.WHITE; }

        if(blackOrWhiteFlag) { return new Color(255-bgColor.getRed(), 255-bgColor.getGreen(), 255-bgColor.getBlue()); }
        
        return new Color(255-bgColor.getRed(), 255-bgColor.getGreen(), 255-bgColor.getBlue()); 
    }

    @Override public void paintComponent(Graphics g) 
    {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        FontMetrics metrics = this.getFontMetrics(this.getFont());

        //If the Button is enabled
        if(this.getModel().isEnabled())
        {
            //If the button is pressed
            if(this.getModel().isPressed()) { g2d.setColor(this.pressedBackgroundColor); }
            //If the user hover overs the button
            else if(this.getModel().isRollover()) { g2d.setColor(this.hoverBackgroundColor); }
            //Idle enabled button
            else { g2d.setColor(this.getBackground()); }
        }
        //If the button is disabled
        else { g2d.setColor(this.disabledBackgroundColor); }

        //Create the button as well as write text on the button
        g2d.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), this.arcWidth, this.arcHeight);
        g2d.setPaint(this.invertFontColor ? this.getInvertedFontColor(g2d.getColor()) : this.fontColor);

        int text_x_pos = this.getWidth() - metrics.stringWidth(this.getText());

        if(this.getHorizontalAlignment() == JButton.CENTER) { text_x_pos /= 2; }
        else if(this.getHorizontalAlignment() == JButton.RIGHT) {/*Text is already right aligned in intialization*/}
        else { text_x_pos = 0; }

        g2d.drawString(this.getText(), text_x_pos, (this.getHeight()-metrics.getHeight())/2 + metrics.getAscent());
        g2d.dispose();

        //Create border around the button
        g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setColor(this.borderColor);
        g2d.drawRoundRect(0, 0, this.getWidth(), this.getHeight(), this.arcWidth, this.arcHeight);
        g2d.dispose();
    }
}
