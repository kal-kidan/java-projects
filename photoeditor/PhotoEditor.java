  
package photoeditor; 
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.Kernel;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.color.ColorSpace;
import java.awt.image.BandCombineOp;
import java.awt.image.ColorConvertOp;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
public class PhotoEditor extends JFrame {
 JButton sharpenButton, blurringButton, edButton,grayButton;
 JButton btrotateClockwise;
 JButton btrotateCounterClockwise;
 JButton btZoomIn;
 JButton btZoomOut;
 JPanel bottompanel;
  JButton redBandButton, greenBandButton, blueBandButton, inverseBandButton,
      middleBandButton;
 boolean stop=true;
 int iw;
 int ih;
 int x;
 int y;
 int adjX;
 int adjY;
 int adjW;
 int adjH;
 int mX;
 int mY;
 int orWidth;
 int orHeight;
 boolean rotate;
 double radian=0.0; 
 JLabel l1,l2,l3,l4;
 Dimension ds;
    //declaring menubar,menuitem,menu
 DrawImage drawimage;
 JMenuBar menubar;
 JMenu filemenu;
 JMenu editmenu;
 JMenu insertmenu;
 JMenu resizeandrotatemenu;
 JMenu cancelmenu;
 JMenuItem menuopen;
 JMenuItem menusaveas;
 JMenuItem menusave;
 JMenuItem menuexit; 
 JMenuItem menubright;  
 JMenuItem menuresize;
 JMenuItem menurotate;
 JMenuItem menuaddtext;
 JMenuItem menucancel;
 String filename;
 String fontName;
 int fontSize;
 String text;
 Dimension screendimension;
 int screenheight;
 int screenwidth;
  JFileChooser filechooser;
   HashMap<Integer, String> imgFiles;
  PhotoEditor () throws AWTException{
      //instanitiate the declared variable
      setLayout(new BorderLayout(64,34));
      drawimage=new DrawImage();
      Container container=getContentPane();
  container.add(drawimage,BorderLayout.CENTER);
  getContentPane().setBackground(Color.WHITE);
  setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
   screendimension=getToolkit().getScreenSize(); //get the screen size   
   screenwidth=(int)screendimension.getWidth(); // get the screen width
   screenheight=(int)screendimension.getHeight();//get the screen height
    btrotateClockwise=new JButton("rotatec"); 
  btrotateClockwise.setBackground(Color.BLACK); 
  btrotateClockwise.setForeground(Color.MAGENTA);   
  btrotateClockwise.addActionListener(new PhotoStudioButtonHandler());

  btrotateCounterClockwise=new JButton("rotatecc");
  btrotateCounterClockwise.setBackground(Color.BLACK);
  btrotateCounterClockwise.addActionListener(new PhotoStudioButtonHandler());
  btrotateCounterClockwise.setForeground(Color.MAGENTA);

  btZoomIn=new JButton ("zoomin");
  btZoomIn.setBackground(Color.BLACK);
  btZoomIn.addActionListener(new PhotoStudioButtonHandler());
  btZoomIn.setForeground(Color.MAGENTA);   
  
  btZoomOut=new JButton ("zoomout");
  btZoomOut.setBackground(Color.BLACK);
  btZoomOut.addActionListener(new PhotoStudioButtonHandler());
  btZoomOut.setForeground(Color.MAGENTA);
  
   bottompanel=new JPanel();
  bottompanel.setLayout(new GridLayout(1,1));
 bottompanel.add(btrotateClockwise);
  bottompanel.add(btrotateCounterClockwise);
  bottompanel.add(btZoomIn);
  bottompanel.add(btZoomOut);
  bottompanel.setBackground(Color.BLACK);
  add(bottompanel, BorderLayout.SOUTH);
 
  menubar=new JMenuBar();
 filemenu=new JMenu("File");
  filemenu.setMnemonic(KeyEvent.VK_F);

  menuopen=new JMenuItem("Open...");
  menuopen.setMnemonic(KeyEvent.VK_O);
  menuopen.addActionListener(new PhotoStudioHandler());

  menusaveas=new JMenuItem("Save as...");
  menusaveas.setMnemonic(KeyEvent.VK_S);
  menusaveas.addActionListener(new PhotoStudioHandler());
  
  menusave=new JMenuItem("Save");
  menusave.setMnemonic(KeyEvent.VK_V);
  menusave.addActionListener(new PhotoStudioHandler());  
  
  menuexit=new JMenuItem("Exit");
  menuexit.setMnemonic(KeyEvent.VK_X);
  menuexit.addActionListener(new PhotoStudioHandler());
  
  filemenu.add(menuopen);
  filemenu.add(menusaveas);
  filemenu.add(menusave);
 filemenu.add(menuexit);  

  editmenu=new JMenu("Edit");
  editmenu.setMnemonic(KeyEvent.VK_E);
  
  menubright=new JMenuItem("Image brightness");
  menubright.setMnemonic(KeyEvent.VK_B);
  menubright.addActionListener(new PhotoStudioHandler());
  
  menuaddtext=new JMenuItem("Add text on image");
  menuaddtext.setMnemonic(KeyEvent.VK_A);
  menuaddtext.addActionListener(new PhotoStudioHandler());  

  menuresize=new JMenuItem("Image resize");
  menuresize.setMnemonic(KeyEvent.VK_R);
  menuresize.addActionListener(new PhotoStudioHandler());
 
  menurotate=new JMenuItem("Image rotation");
  menurotate.setMnemonic(KeyEvent.VK_T);
  menurotate.addActionListener(new PhotoStudioHandler());

  menucancel=new JMenuItem("Cancel editing");
  menucancel.setMnemonic(KeyEvent.VK_L);
  menucancel.addActionListener(new PhotoStudioHandler());
  
  insertmenu=new JMenu("Insert");
  insertmenu.setMnemonic(KeyEvent.VK_I);
  insertmenu.add(menuaddtext);
  
  editmenu.add(menubright);
 
  resizeandrotatemenu=new JMenu("Resize And Rotatemenu");
  resizeandrotatemenu.setMnemonic(KeyEvent.VK_R);
  
  resizeandrotatemenu.add(menuresize);
  resizeandrotatemenu.add(menurotate);
  
  cancelmenu=new JMenu("Cancel");
  cancelmenu.setMnemonic(KeyEvent.VK_C);
  cancelmenu.add(menucancel);
  menubar.add(filemenu);
  menubar.add(insertmenu);
  menubar.add(editmenu);
  menubar.add(cancelmenu);
  menubar.add(resizeandrotatemenu);
  setJMenuBar(menubar);
 filemenu.setFont(new Font( Font.SANS_SERIF,Font.PLAIN,15));
 insertmenu.setFont(new Font( Font.SANS_SERIF,Font.PLAIN,15));
 editmenu.setFont(new Font( Font.SANS_SERIF,Font.PLAIN,15));
 resizeandrotatemenu.setFont(new Font( Font.SANS_SERIF,Font.PLAIN,15));
 cancelmenu.setFont(new Font( Font.SANS_SERIF,Font.PLAIN,15));
 menubar.setBackground(new Color(12,14,18));
  filemenu.setForeground(Color.cyan);
  insertmenu.setForeground(Color.cyan);
  editmenu.setForeground(Color.cyan);
  resizeandrotatemenu.setForeground(Color.cyan);
  cancelmenu.setForeground(Color.cyan);
   filechooser = new JFileChooser();
      FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "gif","bmp","png");
      filechooser .setFileFilter(filter);
      filechooser .setMultiSelectionEnabled(true);
      enableSaving(false);
      l1=new JLabel("sharpening.png");
      l1=new JLabel("blur.png");
      l1=new JLabel("edgedetect.png");
      l1=new JLabel("gray.png");
      JPanel panel3 = new JPanel(new GridLayout(4,1,20,20));
       sharpenButton = new JButton("Sharpen");
    sharpenButton.addActionListener(new ButtonListener());
    blurringButton = new JButton("Blur");
    blurringButton.addActionListener(new ButtonListener());
    edButton = new JButton("Edge Detect");
     grayButton = new JButton("Gray effect");
     grayButton.addActionListener(new ButtonListener());
    edButton.addActionListener(new ButtonListener());
    panel3.add(sharpenButton);
    panel3.add(blurringButton);
    panel3.add(edButton);
    panel3.add(grayButton);
    sharpenButton.setBackground(new Color(64,74,100));
    blurringButton.setBackground(new Color(64,74,100));
    edButton.setBackground(new Color(64,74,100)); 
    sharpenButton.setForeground(new Color(40,214,149));
    blurringButton.setForeground(new Color(40,214,149));
    edButton.setForeground(new Color(40,214,149)); 
   grayButton.setForeground(new Color(40,214,149));
   grayButton.setBackground(new Color(64,74,100)); 
    add(BorderLayout.WEST, panel3);
  JPanel panel4 = new JPanel();
    panel4.setLayout(new GridLayout(4, 1,20,20));
    redBandButton = new JButton("Red");
    redBandButton.addActionListener(new ButtonListener());
    greenBandButton = new JButton("Green");
    greenBandButton.addActionListener(new ButtonListener());
    blueBandButton = new JButton("Blue");
    blueBandButton.addActionListener(new ButtonListener());
    middleBandButton = new JButton("normal");
    middleBandButton.addActionListener(new ButtonListener());
    
    blueBandButton.setForeground(new Color(40,214,149));
    blueBandButton.setBackground(new Color(64,74,100)); 
    greenBandButton.setForeground(new Color(40,214,149));
    greenBandButton.setBackground(new Color(64,74,100));
    redBandButton.setForeground(new Color(40,214,149));
    redBandButton.setBackground(new Color(64,74,100));
    middleBandButton.setForeground(new Color(40,214,149));
    middleBandButton.setBackground(new Color(64,74,100));
    panel4.add(redBandButton);
    panel4.add(blueBandButton);
    panel4.add(greenBandButton);
    panel4.add(middleBandButton);
    add(BorderLayout.EAST,panel4);
 } 
  class ButtonListener implements ActionListener {
       public void actionPerformed(ActionEvent e) {
      JButton button = (JButton) e.getSource();

      if (button.equals(sharpenButton)) {
        drawimage.sharpen();
        enableSaving(true);
        drawimage.repaint();
      } else if (button.equals(blurringButton)) {
        drawimage.blur();
        enableSaving(true);
        drawimage.repaint();
      } else if (button.equals(edButton)) {
        drawimage.edgeDetect();
        enableSaving(true);
        drawimage.repaint();
      }
      else if (button.equals(grayButton)) {
        drawimage.grayOut();
        enableSaving(true);
        drawimage.repaint();
      }
       if (button.equals(redBandButton)) {
        drawimage.bandCombine(drawimage.RED_BAND_MATRIX);
        enableSaving(true);
        drawimage.repaint();
      } else if (button.equals(greenBandButton)) {
        drawimage.bandCombine(drawimage.GREEN_BAND_MATRIX);
        enableSaving(true);
        drawimage.repaint();
      } else if (button.equals(blueBandButton)) {
        drawimage.bandCombine(drawimage.BLUE_BAND_MATRIX);
        enableSaving(true);
        drawimage.repaint();
      } else if (button.equals(inverseBandButton)) {
        drawimage.bandCombine(drawimage.INVERSE_BAND_MATRIX);
        enableSaving(true);
        drawimage.repaint();
      } else if (button.equals(middleBandButton)) {
        drawimage.bandCombine(drawimage.AVERAGE_BAND_MATRIX);
        enableSaving(true);
        drawimage.repaint();
      }
    }
  }
   
  
  
 // class that implement action listsener for the menu bar and instanitiate all class
  class PhotoStudioHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
             String classname=event.getActionCommand();
            
  if(classname.equals("Open..."))
                 {
                OpenImage();
      
                   }
  else if(classname.equals("Save as...")) 
    {
    SaveAs(); 
      
     }
  else if(classname.equals("Save"))
    {
     
   Save(); 
     }
  else if(classname.equals("Add text on image") )
    {
     AddText(); 
    }

  else if(classname.equals("Image brightness"))
    {
     
      ImageBrightness(); 
    }
   
  else if(classname.equals("Image resize"))
    {
     
     ImageResize();
    
     }
  else if(classname.equals("Image rotation") )
    {
     
                 try {
                     Imagerotation();
                     enableSaving(true);
                 } catch (AWTException ex) {
                     
                 }
     } 
   
  else if(classname.equals("Cancel editing")) 
   {
       drawimage.setImgFileName(filename);
        drawimage.reset();
   } 
  if(classname.equals("Exit"))
                 {
                System.exit(0);
      
                   }
        }
  
 
   
    
  }
 
class PhotoStudioButtonHandler implements ActionListener {
public void actionPerformed(ActionEvent e){
     ds=getToolkit().getScreenSize();  
  mX=(int)ds.getWidth()/2;  
  mY=(int)ds.getHeight()/2; 
  filename=null; 
  rotate=false;  
  adjX=0;
  adjY=0;
  adjW=0;
  adjH=0;
   if(e.getSource()==btrotateClockwise)
    {
         try {
             rotate=true;
             drawimage.rotateCCounterClockwise();
             drawimage.repaint();
              enableSaving(true);
         } catch (AWTException ex) {
             
         }
    }
   else if(e.getSource()==btrotateCounterClockwise)
    {
    rotate=true;
         try {
             drawimage.rotateCounterClockwise();
             enableSaving(true);
         } catch (AWTException ex) {
              
         }
    drawimage.repaint();
    }
   else if(e.getSource()==btZoomIn)
    {
    drawimage.zoomIn();
    enableSaving(true);
   
    }
   else if(e.getSource()==btZoomOut)
    {
    drawimage.zoomOut();
    enableSaving(true);
    
    }
    
   
     
         
    }}
 

public void  OpenImage(){ 
    
 
 if(JFileChooser.APPROVE_OPTION==filechooser.showOpenDialog(null)) {   
   filename=filechooser.getSelectedFile().toString();
   drawimage.bandcombine=false;
  
 }
 drawimage.prepareImage(filename);
 drawimage.repaint();
}
public void SaveAs(){
    showSaveFileDialog();    
}
public void Save(){
     drawimage.saveToFile(filename); 
}
public void  AddText(){
   new  AddText();
   enableSaving(true);
}
public void ImageBrightness(){
   ImageBrightness ib=new ImageBrightness(); 
    if(drawimage.imageLoaded)
     ib.enableSlider(true); 
}
 
 public void ImageResize(){
     new ImageResize();
     enableSaving(true);
}
  public void Imagerotation() throws AWTException{
       Double rad = null;
            rad=Math.PI/2;
      drawimage.rotateImage(rad);
}
  class  AddText extends JFrame implements ActionListener {
  JPanel panel;
  JPanel panel2;
  JPanel panel3;
  JTextArea txtText;
  JComboBox cbFontNames;
  JComboBox cbFontSizes;
  JButton btOK;
  JButton btSetColor;
  String seFontName;
  Color colorText;
  int seFontSize;
  AddText(){
      String [] fontsize=new String [20];
   GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment(); 
   String[] fontname=ge.getAvailableFontFamilyNames();
  colorText=null;
  setTitle("Add text to the image");
  setPreferredSize(new Dimension(400,350));
  
  btOK=new JButton("OK");
  btOK.setBackground(new Color(134,156,120));
  btOK.setForeground(Color.BLACK);  
  btOK.addActionListener(this);
  
  btSetColor=new JButton("Set text color");
  btSetColor.setBackground(new Color(94,76,89));
  btSetColor.setForeground(Color.BLACK);  
  btSetColor.addActionListener(this);
  
  txtText=new JTextArea(1,40);
  cbFontNames=new JComboBox(fontname);
  cbFontSizes=new JComboBox();
   for(int i=15;i<25;i++){
    cbFontSizes.addItem(i);
   
  }
  panel=new JPanel(new GridLayout(2,1));
  panel2=new JPanel(new GridLayout(4,1));
  panel3=new JPanel(new GridLayout(2,1));
  
  panel.add(new JLabel("Add Text Here:"));
  panel.add(txtText);
  
  panel2.add(new JLabel("Font Style:"));  
  panel2.add(cbFontNames);
  
  panel2.add(new JLabel("Font Size:"));  
  panel2.add(cbFontSizes);
  
  panel3.add(btSetColor);
  panel3.add(btOK);
  
  setLayout(new GridLayout(3,1));
  add(panel);
  add(panel2);
  add(panel3);
  setVisible(true);
  pack();
 getContentPane().setBackground(new Color(34,56,200));
  }
 public void actionPerformed(ActionEvent e){
   if(e.getSource()==btOK){ //the button OK is clicked so the text is ready to place on the image
    drawimage.actionDraw=true;
    String textDraw=txtText.getText(); 
    String fontName=cbFontNames.getSelectedItem().toString();
    int fontSize=Integer.parseInt(cbFontSizes.getSelectedItem().toString());
    drawimage.setText(textDraw,fontName,fontSize,colorText);
    enableSaving(true);
    dispose();
    }
   else if(e.getSource()==btSetColor){ //show color chooser dialog for color selection
    JColorChooser jser=new JColorChooser();   
    colorText=jser.showDialog(this,"Color Chooser",Color.BLACK);
     
   }
  }
}
  public class ImageResize extends JFrame implements ActionListener {
  JPanel panel;
  JTextField txtWidth;
  JTextField txtHeight;
  JButton btOK;
  ImageResize(){
  setTitle("Image resize");
  setPreferredSize(new Dimension(400,100));
  setResizable(false);
  btOK=new JButton("OK");
  btOK.setBackground(Color.BLACK);
  btOK.setForeground(Color.BLUE);  
  btOK.addActionListener(this);
  txtWidth=new JTextField(4);
  txtHeight=new JTextField(4);
  panel=new JPanel();
  panel.setLayout(new GridLayout(2,2));
  panel.add(new JLabel("Width:"));
  panel.add(txtWidth);
  panel.add(new JLabel("Height:"));
  panel.add(txtHeight);
  panel.setBackground(Color.GRAY);
  add(panel, BorderLayout.CENTER);
  add(btOK,BorderLayout.SOUTH);
  setVisible(true);
  pack();
 getContentPane().setBackground(new Color(245,245,245));
  }
  
  //This method works when you click the OK button to resize the image
  public void actionPerformed(ActionEvent e){
   if(e.getSource()==btOK){
    drawimage.actionResized=true;     
    drawimage.resizeImage(Integer.parseInt(txtWidth.getText()),Integer.parseInt(txtHeight.getText()));
    enableSaving(true);
    drawimage.repaint();
    }
  }}
  public class ImageBrightness extends JFrame implements ChangeListener{
  JSlider slider;
 
  ImageBrightness(){
  addWindowListener(new WindowAdapter(){
     public void windowClosing(WindowEvent e){
      dispose();
      
     }
    });
  Container cont=getContentPane();  
  slider=new JSlider(-10,10,0); 
  slider.setEnabled(false);
  slider.addChangeListener(this);
  cont.add(slider,BorderLayout.CENTER); 
  slider.setEnabled(true);
  setTitle("Image brightness");
  setPreferredSize(new Dimension(300,100));
  setVisible(true);
  pack();
  enableSlider(false);
  }
  public void enableSlider(boolean enabled){
   slider.setEnabled(true);
  }
  public void stateChanged(ChangeEvent e){
    drawimage.setValue(slider.getValue()/10.0f);
    drawimage.actionSlided=true;   
    drawimage.filterImage();
    drawimage.repaint();
    enableSaving(true);
 
   
  }

 }
  public void enableSaving(boolean f){
  menusaveas.setEnabled(f);
  menusave.setEnabled(f); 
  
  }
   public void showSaveFileDialog(){
       int returnVal = filechooser.showSaveDialog(this);
     if(returnVal == JFileChooser.APPROVE_OPTION) {  
   String filen=filechooser.getSelectedFile().toString(); 
                drawimage.saveToFile(filen);  
   }
   }
    public static void main(String[] args) throws AWTException {
    PhotoEditor  pst=new  PhotoEditor();
   int height=pst.screenheight;
   int width=pst.screenwidth;
   pst.setSize(width,height );
   pst.setTitle("BRANA PHOTO EDITOR");
   pst.setVisible(true);
   pst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
    }
    
}
 class DrawImage extends Canvas{
  PhotoEditor pe;
  Image orImg=null;
  BufferedImage orBufferedImage;
  BufferedImage bimg; 
  BufferedImage bimg1; 
  BufferedImage biDest;
  int imgindex=0;
  float e;
  float radian;
  Dimension ds;
  int mX;
  int mY;
  int x;
  int y;
  int adjX;
 int adjY;
 int adjW;
 int adjH;
  static boolean imageLoaded;
  boolean actionSlided;
  boolean actionResized;
  boolean actionCompressed;
  boolean actionTransparent;
  boolean actionRotated;
  boolean actionDraw;
  boolean drawn;
  boolean zoom;
  boolean bandcombine;
  MediaTracker mt;
  static Color c;
  Color colorTextDraw;
  Robot rb;
  boolean dirHor;
  boolean effect;
  String imgFileName;
  String fontName;
  int fontSize;
  String textToDraw;
   Dimension screendimension;
  int screenheight;
  int screenwidth;
  int zoomx;
  int zoomy;
  int zoombix;
  int zoombiy;
    
 
  Raster srcRaster;

  WritableRaster dstRaster;

  BufferedImage bi;

  Graphics2D g2d2;
  static final float RED_BAND_MATRIX[][] = { { 1.0f, 0.0f, 0.0f },
      { 0.0f, 0.0f, 0.0f }, { 0.0f, 0.0f, 0.0f } };
 
  static final float GREEN_BAND_MATRIX[][] = { { 0.0f, 0.0f, 0.0f },
      { 0.0f, 1.0f, 0.0f }, { 0.0f, 0.0f, 0.0f } };
 
  static final float BLUE_BAND_MATRIX[][] = { { 0.0f, 0.0f, 0.0f },
      { 0.0f, 0.0f, 0.0f }, { 0.0f, 0.0f, 1.0f } };
 
  static final float INVERSE_BAND_MATRIX[][] = { { -1.0f, 0.0f, 0.0f },
      { 0.0f, -1.0f, 0.0f }, { 0.0f, 0.0f, -1.0f } };

 
  static final float AVERAGE_BAND_MATRIX[][] = { { 0.5f, 0.0f, 0.0f },
      { 0.0f, 0.5f, 0.0f }, { 0.0f, 0.0f, 0.5f } };
  public DrawImage() throws AWTException{
       
    rb=new Robot();
    screendimension=getToolkit().getScreenSize();  
    screenwidth=(int)screendimension.getWidth();  
    screenheight=(int)screendimension.getHeight(); 
    int mx=screenwidth/2;
    int my=screenheight/2;
      
    addMouseListener(new Mousexy());  
   addKeyListener(new KList());
  }
  public void initialize(){
   effect=false;
   imageLoaded=false; 
   actionSlided=false;
   actionResized=false;
   actionCompressed=false;
   actionTransparent=false;
   actionRotated=false;
   actionDraw=false;
   drawn=false;
   dirHor=false;
   zoom=false;
   c=null;
   radian=0.0f;
   e=0.0f;
     
   }
  
  public void paint(Graphics g){
   Graphics2D g2d=(Graphics2D)g;   
      
   if(imageLoaded){
        x=screenwidth/2-bimg.getWidth()/2;
     y=screenheight/2-bimg.getHeight()/2;
     x=x-170;
    
    if(actionSlided || actionResized || actionTransparent || actionRotated || drawn||zoom||effect||bandcombine) {
        if(zoom){
          g2d.translate(x,y+adjY); 
          g2d.drawImage(bimg,0,0,null); 
        }
        else{
     g2d.translate(x,y); 
     g2d.drawImage(bimg,0,0,null); 
     
     }}
     
    
    
    else{  
    
     g2d.translate(x,y);  
     g2d.drawImage(orBufferedImage,0,0,null); 
     }
   }
   g2d.dispose();  
  }
   
  class Mousexy extends MouseAdapter{
   
   public void mousePressed(MouseEvent e){ 
      Color color=rb.getPixelColor(e.getX(),e.getY());
    try{   
        setColor(color);    
    if(actionDraw){  
     if(actionSlided || actionResized || actionTransparent || actionRotated || drawn||effect)
      addTextToImage(e.getX()-x,e.getY()-y, bimg);
     else  //add text to the original image
      addTextToImage(e.getX()-x,e.getY()-y, orBufferedImage);
     }
    }catch(Exception ie){}
   }
  }
 
 class KList extends KeyAdapter{
  public void keyPressed(KeyEvent e){
   if(e.getKeyCode()==27){ //ESC is pressed to stop drawing the text on the image
    actionDraw=false;
    textToDraw="";
   fontName="";
    fontSize=0;
    }
   }
  }
  public void addTextToImage(int x,int y, BufferedImage img){
  BufferedImage bi=(BufferedImage)createImage(img.getWidth(),img.getHeight());  
  Graphics2D  g2d=(Graphics2D)bi.createGraphics();
  g2d.setFont(new Font(fontName,Font.BOLD,fontSize));
  g2d.setPaint(colorTextDraw);
  g2d.drawImage(img,0,0,null);
  g2d.drawString(textToDraw,x,y);
  bimg=bi;
  drawn=true;
  g2d.dispose();
  repaint(); 
  }

   public void prepareImage(String filename){
   initialize();
   try{
   mt=new MediaTracker(this);    
   orImg=Toolkit.getDefaultToolkit().getImage(filename); 
   mt.addImage(orImg,0);
   mt.waitForID(0);  
   int width=orImg.getWidth(null);
   int height=orImg.getHeight(null);
   orBufferedImage=createBufferedImageFromImage(orImg,width,height,false); 
    biDest = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB );
   bimg = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);  
   imageLoaded=true;  
    
   }catch(Exception e){System.exit(-1);}
  }
   public BufferedImage createBufferedImageFromImage(Image image, int width, int height, boolean tran)
   { 
       BufferedImage dest ;
  if(tran) 
      dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
  else
   dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      Graphics2D g2 = dest.createGraphics();
      g2.drawImage(image, 0, 0, null);
      g2.dispose();
      return dest;
   }
   public void setText(String text,String fName, int fSize, Color color){
   textToDraw=text;
   fontName=fName;
   fontSize=fSize;
   if(color==null)
    colorTextDraw=new Color(0,0,0);
   else
    colorTextDraw=color;
  }
 public void resizeImage(int w,int h){
    BufferedImage bi=(BufferedImage)createImage(w,h);
    Graphics2D g2d=(Graphics2D)bi.createGraphics();
    if(actionSlided || actionTransparent || actionRotated ||drawn||zoom||effect||bandcombine)
     g2d.drawImage(bimg,0,0,w,h,null);
    else
     g2d.drawImage(orImg,0,0,w,h,null);
    bimg=bi;
    g2d.dispose();
   
  }
 public void makeImageRotate(BufferedImage image,int w,int h,Double radian) throws AWTException{
   PhotoEditor phe=new PhotoEditor();
   BufferedImage bi=(BufferedImage)createImage(w,h);
   Graphics2D  g2d=(Graphics2D)bi.createGraphics();       
   g2d.translate(w/2,h/2); 
   g2d.rotate(radian);  
   g2d.translate(-h/2,-w/2);  
   g2d.drawImage(image,0,0,null);  
   bimg=bi;  
   phe.enableSaving(true);
   g2d.dispose();  
   
  }
   
  public void rotateImage(Double radian) throws AWTException{
    BufferedImage bi;
    
    if(actionSlided || actionResized || actionTransparent || actionRotated || drawn||zoom||effect||bandcombine){
     bi=bimg;     
    }
    
    else{
     bi=orBufferedImage;
    } 

    makeImageRotate(bi,bi.getHeight(),bi.getWidth(),radian);
        
    actionRotated=true;   
 
    repaint();  
     
   }
    
   public void filterImage(){
   float[] elements = {0.0f, 1.0f, 0.0f, -1.0f,e,1.0f,0.0f,0.0f,0.0f}; 
   Kernel kernel = new Kernel(3, 3, elements);   
   ConvolveOp cop = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);  
   //the kernel
   bimg= new BufferedImage(orBufferedImage.getWidth(),orBufferedImage.getHeight(),BufferedImage.TYPE_INT_RGB);
   cop.filter(orBufferedImage,bimg); 
    
   
  }
   
  public void setValue(float value){ 
   e=value;
  }
  
public void setColor(Color color){
   c=color;   
  }
   
 
  public void saveToFile(String filename){
  String ftype=filename.substring(filename.lastIndexOf('.')+1);
  try{
   if(actionSlided || actionResized || actionTransparent || actionRotated || drawn||zoom||effect||bandcombine)
    ImageIO.write(bimg,ftype,new File(filename));
    }catch(IOException e){System.out.println("Error in saving the file");}
  }
  public void setImgFileName(String fname){
   imgFileName=fname;
  }
    public void reset(){
   if(imageLoaded){
   prepareImage(imgFileName);
   repaint();
   }}
      public void bandCombine(float[][] bandCombineMatrix) {
        bandcombine=true;
        
    BufferedImage bi;
    bi = new BufferedImage(orBufferedImage.getWidth(this), orBufferedImage.getHeight(this), BufferedImage.TYPE_INT_RGB);
    g2d2 = bi.createGraphics();
    g2d2.drawImage(orBufferedImage, 0, 0, this);
    srcRaster = bi.getRaster();
    biDest = new BufferedImage(orBufferedImage.getWidth(this), orBufferedImage.getHeight(this), BufferedImage.TYPE_INT_RGB);
    dstRaster = (WritableRaster) biDest.getRaster();
    BandCombineOp bandCombineOp = new BandCombineOp(bandCombineMatrix, null);
    bandCombineOp.filter(srcRaster, dstRaster);
    bimg = biDest;
  }
public void rotateCCounterClockwise() throws AWTException{
    Double rad = null;
            rad=Math.PI/2;
            rotateImage(rad);
}
public void rotateCounterClockwise() throws AWTException{
Double rad = null;
            rad=-Math.PI/2;
             rotateImage(rad);
}
public void zoomIn(){
     zoom=true;
       adjW+=20;
        adjH+=20;
      if(adjY>-320){
      adjY-=10;
      adjX+=10;
      }
       zoom(adjW,adjH);  
}
public void zoomOut(){
    zoom=true;
      adjH-=20;
      adjW-=20;
      if(adjY<720){
      adjY+=10;
      adjX+=10;
      }
   zoom(adjW,adjH);
    
}
public void zoom(int w,int h){
    zoom=true;
    BufferedImage bi ;
    Graphics2D g2d ;
        bi=(BufferedImage)createImage(w+orBufferedImage.getWidth(),h+orBufferedImage.getHeight());
        g2d=(Graphics2D)bi.createGraphics();   
    g2d.drawImage(orBufferedImage,0,0,w+orBufferedImage.getWidth(),h+orBufferedImage.getHeight(),null);
   
    bimg=bi;
    repaint();
 }
 

  public void sharpen() {
   
      bi = orBufferedImage;
        effect=true; 
    float data[] = { -2.0f, -1.0f, 0.0f, -1.0f, 1.0f, 1.0f,0.0f, 1.0f, 2.0f };
    Kernel kernel = new Kernel(3, 3, data);
    ConvolveOp convolve = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP,
        null);
    convolve.filter(bi, biDest);
    bimg = biDest;
  
  }

  public void blur() {
      bi = orBufferedImage;
       effect=true; 
    float data[] = {0.1f,0.1f,0.1f,0.1f,0.1f,0.1f,0.1f,0.1f,0.1f,0.1f};
    Kernel kernel = new Kernel(3, 3, data);
    ConvolveOp convolve = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP,
        null);
    convolve.filter(bi,biDest);
    bimg = biDest;
  }

    public void edgeDetect() {
    
         bi = orBufferedImage;
      effect=true;   
    float data[] = { 0.0f, -1.0f, 0.0f, -1.0f, 4.0f, -1.0f, 0.0f, -1.0f,
        0.0f };

    Kernel kernel = new Kernel(3, 3, data);
    ConvolveOp convolve = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP,
        null);
    convolve.filter(bi, biDest);

   bimg= biDest;
  }
    public void grayOut() {
        bi = orBufferedImage;
    ColorConvertOp colorConvert = new ColorConvertOp(ColorSpace .getInstance(ColorSpace.CS_GRAY), null);
    colorConvert.filter(bi, biDest);
  }
   
 
  public static void main(String[] args) throws AWTException {  
      new DrawImage();
  } 
}
  