// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot.subsystems;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.util.Color;


/**
 *
 */
public class ControlPanel extends Subsystem {

  // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

  // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private WPI_TalonSRX spinMotor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
  private final I2C.Port i2cPort = I2C.Port.kOnboard;

  /**
   * A Rev Color Sensor V3 object is constructed with an I2C port as a parameter.
   * The device will be automatically initialized with default parameters.
   */
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

  /**
   * A Rev Color Match object is used to register and detect known colors. This
   * can be calibrated ahead of time or during operation.
   * 
   * This object uses a simple euclidian distance to estimate the closest match
   * with given confidence range.
   */
  private final ColorMatch m_colorMatcher = new ColorMatch();

  /**
   * Note: Any example colors should be calibrated as the user needs, these are
   * here as a basic example.
   */
  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  public ControlPanel() {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
spinMotor = new WPI_TalonSRX(15);


        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);
  }

  @Override
  public void initDefaultCommand() {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  public void periodic() {
    // Put code here to be run every loop

  }

  // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

  // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public void spin() {
    spinMotor.set(.05);
  }

  public void stop() {
    spinMotor.set(0);
  }

  public boolean colormatch(String gameData) {
    /**
     * The method GetColor() returns a normalized color value from the sensor and
     * can be useful if outputting the color to an RGB LED or similar. To read the
     * raw color, use GetRawColor().
     * 
     * The color sensor works best when within a few inches from an object in well
     * lit conditions (the built in LED is a big help here!). The farther an object
     * is the more light from the surroundings will bleed into the measurements and
     * make it difficult to accurately determine its color.
     */
    Color detectedColor = m_colorSensor.getColor();

    /**
     * Run the color match algorithm on our detected color
     */
    System.out.println("gamedata = " + gameData);
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    String colorString;
    if (match.color == kBlueTarget) {
      colorString = "Blue";
    } else if (match.color == kRedTarget) {
      colorString = "Red";
    } else if (match.color == kGreenTarget) {
      colorString = "Green";
    } else if (match.color == kYellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }
    switch (gameData.charAt(0)) {
    case 'B':
      // Blue case code
      if (match.color == kBlueTarget) {
        SmartDashboard.putString("Matched Color", "Blue");
        return true;
      }
      break;
    case 'G':
      // Green case code
      if (match.color == kGreenTarget) {
        SmartDashboard.putString("Matched Color", "Green");
        return true;
      }
      break;

    case 'R':
      // Red case code
      if (match.color == kRedTarget) {
        SmartDashboard.putString("Matched Color", "Red");
        return true;
      }
      break;

    case 'Y':
      // Yellow case code
      if (match.color == kYellowTarget) {
        SmartDashboard.putString(" Matched Color", "Yellow");
        return true;
      }
      break;
    default:
      // This is corrupt data
      break;
    }
    SmartDashboard.putNumber("Color", detectedColor.red);
    SmartDashboard.putString("Detected Color", gameData);
    SmartDashboard.putString("Color Detected", colorString);
    return false;
  }

  public boolean colorCounter() {
    // /**
    // * Method used to count how many times the color sensor reads a color Set up
    // to
    // * count when the starting color matches the current color being read, the
    // * current color is equal to Rcolor When Rcolor is equal to previous color do
    // * nothing when Rcolor is equal to starting color, count one rotation color
    // * count is less than or equal to 7
    // */
   
   int colorCount = 0;
   Color startingColor = null;
   Color readColor = null;
   String startingColorStr = "unknown";
   String readColorStr = "unknown";

   startingColor = m_colorSensor.getColor();
   startingColorStr = getColorString(startingColor);
   while (colorCount <=2) {
     readColor = m_colorSensor.getColor();
     readColorStr = getColorString(readColor);
      if (readColorStr.equals(startingColorStr));
        colorCount = colorCount + 1;
        if (colorCount + 1 >=1);
        System.out.println("It's Working! It's Working!");
      
   }
   
   return true;
   
    /* int colorCount = 0;
    Color prevColor = null;
    Color readColor = null;
    String startingColor = "unknown";
    String prevColorStr = "unknown"; 
    String readColorStr = "unknown";

    readColor = m_colorSensor.getColor();
    startingColor = getColorString(readColor);
    while (colorCount <= 2) {
      readColor = m_colorSensor.getColor();
      prevColor = readColor; 
      prevColorStr = getColorString(prevColor);
      readColorStr = getColorString(readColor);
     if (!readColorStr.equals(prevColorStr)) {
        System.out.println("Step 1 Done");
        if (readColorStr.equals(startingColor)) {
          System.out.println("Step 2 Done");
          colorCount = colorCount + 1;

         System.out.println("Step 3 Done");
        }
        
      }
    }
    return true;
  */ }


  // comments from Lauren 
  private String getColorString(Color detectedColor) {
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    String colorString;
    if (match.color == kBlueTarget) {
      colorString = "Blue";
    } else if (match.color == kRedTarget) {
      colorString = "Red";
    } else if (match.color == kGreenTarget) {
      colorString = "Green";
    } else if (match.color == kYellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }
    return colorString;
  }


  private char GetColorChar(Color color){
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    char colorChar;
    if (match.color == kBlueTarget) {
      colorChar = 'B';
    } else if (match.color == kRedTarget) {
      colorChar = 'R';
    } else if (match.color == kGreenTarget) {
      colorChar = 'G';
    } else if (match.color == kYellowTarget) {
      colorChar = 'Y';
    } else {
      colorChar = 'U';
    }
    return colorChar;
  }
}
