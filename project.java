import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.Timer;

class StopWatch{
    private long startTime;
    private long endTime;
    private long prev_time;
    private boolean check;
    StopWatch(){
        startTime=System.currentTimeMillis();
    }
    public void start(){
        startTime=System.currentTimeMillis();
        check=true;
    }
    public void stop(){
        endTime=System.currentTimeMillis();
        prev_time+=endTime-startTime;
        check=false;
    }
    public long timeElapsed(){
        if (check==true)
            return prev_time+(System.currentTimeMillis()-startTime);
        else
            return prev_time;
    }
    public boolean on(){
        return check;
    }
    public void reset_prev_time(){
        prev_time=0;
    }
}
public class project{
    public static void main(String[] args){
        JFrame frame=new JFrame();
        StopWatch stopwatch = new StopWatch();
        frame.setResizable(true);
        frame.setSize(500,460);
        frame.getContentPane().setBackground(new Color(40, 44, 52));
        frame.setTitle("Stop Watch");
        frame.setLayout(null); 
        
        JPanel panel=new JPanel();
        panel.setBounds(50, 50, 400, 100);
        panel.setBackground(new Color(30, 33, 39));

        JLabel time_box=new JLabel();
        time_box.setText("00 : 00 : 00 : 00");
        time_box.setBounds(50, 50, 400, 100);
        time_box.setVerticalAlignment(JLabel.CENTER);
        time_box.setHorizontalAlignment(JLabel.CENTER);
        time_box.setForeground(new Color(50, 205, 50));
        time_box.setFont(new Font("Monospaced", Font.BOLD, 35));

        JButton button1= new JButton();
        button1.setBounds(70, 250, 140, 60);
        button1.setFocusable(false);
        button1.setText("START");
        button1.setFont(new Font("SansSerif", Font.BOLD, 20));
        button1.setForeground(Color.white);
        button1.setBackground(new Color(70, 130, 180));

        JButton button2 = new JButton();
        button2.setBounds(230, 250, 140, 60);
        button2.setFocusable(false);
        button2.setText("RESET");
        button2.setFont(new Font("SansSerif", Font.BOLD, 20));
        button2.setForeground(Color.white);//text color
        button2.setBackground(new Color(220, 20, 60));

        Timer time=new Timer(10,event->{
            if (stopwatch.on()==true){
                long total_milli=stopwatch.timeElapsed();
                long millis=(total_milli%1000)/10;           
                long total_sec=total_milli/1000;   
                long sec=total_sec%60;
                long min=(total_sec%3600)/60;
                long hour=total_sec/3600;
                String time_on_screen=String.format("%02d : %02d : %02d : %02d",hour,min,sec,millis);
                time_box.setText(time_on_screen);
            }
        });
        button1.addActionListener(event->{
            if (event.getSource()==button1 && stopwatch.on()==true){
                button1.setText("START");
                stopwatch.stop();
                time.stop();    
            }
            else if (stopwatch.on()==false && event.getSource()==button1){
                button1.setText("STOP");
                stopwatch.start();
                time.start();
            }
        });
        button2.addActionListener(event->{
            if (event.getSource()==button2){
                time_box.setText("00 : 00 : 00 : 00");
                stopwatch.reset_prev_time();
            }
        });
        frame.add(button1);
        frame.add(button2);
        frame.add(time_box);
        frame.add(panel);
        frame.setVisible(true);
    }
}