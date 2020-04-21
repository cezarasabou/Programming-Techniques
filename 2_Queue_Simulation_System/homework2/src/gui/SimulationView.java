package gui;

import controller.QueueSimulation;
import exception.InvalidInputException;
import model.Queue;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**The User Interface component
 * @author Cezara
 *
 */

public class SimulationView extends JFrame{

    private JFrame frame = new JFrame("QueueSimulation");
    private JPanel northPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private JPanel southPanel = new JPanel();
    private JLabel minimumArrivalTimeLabel = new JLabel();
    private JLabel maximumArrivalTimeLabel = new JLabel();
    private JLabel minimumServiceTimeLabel = new JLabel();
    private JLabel maximumServiceTimeLabel = new JLabel();
    private JLabel simulationIntervalLabel = new JLabel();
    private JLabel numberOfQueuesLabel = new JLabel();
    private JTextField minimumArrivalTime = new JFormattedTextField();
    private JTextField maximumArrivalTime = new JFormattedTextField();
    private JTextField minimumServiceTime = new JFormattedTextField();
    private JTextField maximumServiceTime = new JFormattedTextField();
    private JTextField simulationInterval = new JFormattedTextField();
    private JTextField numberOfQueues = new JFormattedTextField();
    private Container contentPanel;

    private JLabel ta0Label = new JLabel();
    private JLabel ta1Label = new JLabel();
    private JLabel ta2Label = new JLabel();
    private JLabel ta3Label = new JLabel();
    private JLabel ta4Label = new JLabel();
    private JLabel ta5Label = new JLabel();
    private JLabel ta6Label = new JLabel();
    private JLabel ta7Label = new JLabel();
    private JLabel ta8Label = new JLabel();
    private JLabel ta9Label = new JLabel();
    private JTextArea ta0 = new JTextArea(1, 30);
    private JTextArea ta1 = new JTextArea(1, 30);
    private JTextArea ta2 = new JTextArea(1, 30);
    private JTextArea ta3 = new JTextArea(1, 30);
    private JTextArea ta4 = new JTextArea(1, 30);
    private JTextArea ta5 = new JTextArea(1, 30);
    private JTextArea ta6 = new JTextArea(1, 30);
    private JTextArea ta7 = new JTextArea(1, 30);
    private JTextArea ta8 = new JTextArea(1, 30);
    private JTextArea ta9 = new JTextArea(1, 30);

    private JLabel avg0Label = new JLabel();
    private JLabel avg1Label = new JLabel();
    private JLabel avg2Label = new JLabel();
    private JLabel avg3Label = new JLabel();
    private JLabel avg4Label = new JLabel();
    private JLabel avg5Label = new JLabel();
    private JLabel avg6Label = new JLabel();
    private JLabel avg7Label = new JLabel();
    private JLabel avg8Label = new JLabel();
    private JLabel avg9Label = new JLabel();

    private JTextArea avg0 = new JTextArea(1, 20);
    private JTextArea avg1 = new JTextArea(1, 20);
    private JTextArea avg2 = new JTextArea(1, 20);
    private JTextArea avg3 = new JTextArea(1, 20);
    private JTextArea avg4 = new JTextArea(1, 20);
    private JTextArea avg5 = new JTextArea(1, 20);
    private JTextArea avg6 = new JTextArea(1, 20);
    private JTextArea avg7 = new JTextArea(1, 20);
    private JTextArea avg8 = new JTextArea(1, 20);
    private JTextArea avg9 = new JTextArea(1, 20);




    private JButton startButton = new JButton("start");

    private static int maxNumberOfQueues;
    private static int minArrivalTime;
    private static int maxArrivalTime;
    private static int minServiceTime;
    private static int maxServiceTime;
    private static int simulationIntervalVar;

    private ArrayList<JTextArea> queues = new ArrayList<>();
    private ArrayList<JTextArea> averages = new ArrayList<>();
    QueueSimulation qs = new QueueSimulation(this);

    /**
     *Constructor
     **/

    public SimulationView(){
       prepareGUI();
       addComponents();
       addActionEvents();
    }
    /**
     * Method that sets the basic layout of the application
     * @return no return
     **/
    public void prepareGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * adds components to the frame
     * @return No return value
     **/
    public void addComponents(){

        contentPanel = frame.getContentPane();
        contentPanel.setBackground(Color.DARK_GRAY);

        int widthLabel = 150;
        int height = 20;
        int xLabel = 20;
        int xField = 200;
        int baseY = 20;

        numberOfQueuesLabel.setBounds(xLabel, baseY, widthLabel, height);
        numberOfQueuesLabel.setForeground(Color.white);
        numberOfQueuesLabel.setText("number of queues:");
        frame.add(numberOfQueuesLabel);

        numberOfQueues.setBounds(xField, baseY, widthLabel, height);
        numberOfQueues.setFont(new Font("Arial", Font.BOLD, 16));
        numberOfQueues.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.add(numberOfQueues);

        minimumServiceTimeLabel.setBounds(xLabel, baseY + 30, widthLabel, height);
        minimumServiceTimeLabel.setForeground(Color.white);
        minimumServiceTimeLabel.setText("min service time:");
        frame.add(minimumServiceTimeLabel);

        minimumServiceTime.setBounds(xField, baseY + 30, widthLabel, height);
        minimumServiceTime.setFont(new Font("Arial", Font.BOLD, 16));
        minimumServiceTime.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.add(minimumServiceTime);


        maximumServiceTimeLabel.setBounds(xLabel, baseY + 60, widthLabel, height);
        maximumServiceTimeLabel.setForeground(Color.white);
        maximumServiceTimeLabel.setText("max service time:");
        frame.add(maximumServiceTimeLabel);

        maximumServiceTime.setBounds(xField, baseY + 60, widthLabel, height);
        maximumServiceTime.setFont(new Font("Arial", Font.BOLD, 16));
        maximumServiceTime.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.add(maximumServiceTime);


        minimumArrivalTimeLabel.setBounds(xLabel, baseY + 90, widthLabel, height);
        minimumArrivalTimeLabel.setForeground(Color.white);
        minimumArrivalTimeLabel.setText("min arrival time:");
        frame.add(minimumArrivalTimeLabel);

        minimumArrivalTime.setBounds(xField, baseY + 90, widthLabel, height);
        minimumArrivalTime.setFont(new Font("Arial", Font.BOLD, 16));
        minimumArrivalTime.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.add(minimumArrivalTime);


        maximumArrivalTimeLabel.setBounds(xLabel,baseY + 120,widthLabel,height);
        maximumArrivalTimeLabel.setForeground(Color.white);
        maximumArrivalTimeLabel.setText("max arrival time:");
        frame.add(maximumArrivalTimeLabel);

        maximumArrivalTime.setBounds(xField, baseY + 120, widthLabel, height);
        maximumArrivalTime.setFont(new Font("Arial", Font.BOLD, 16));
        maximumArrivalTime.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.add(maximumArrivalTime);

        simulationIntervalLabel.setBounds(xLabel,baseY + 150,widthLabel,height);
        simulationIntervalLabel.setForeground(Color.white);
        simulationIntervalLabel.setText("simulation interval:");
        frame.add(simulationIntervalLabel);

        simulationInterval.setBounds(xField, baseY + 150, widthLabel, height);
        simulationInterval.setFont(new Font("Arial", Font.BOLD, 16));
        simulationInterval.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.add(simulationInterval);


        startButton.setBounds(500, 100, widthLabel, height);
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.setBackground(new Color(239, 188, 2));
        frame.add(startButton);


        int x = 100;
        int xl =50;
        int y = 300;
        int width = 350;
        int avgWidth = 40;
        int height1 = 20;

        ta0.setBounds(x,y,width, height1);
        frame.add(ta0);
        avg0.setBounds(x+400,y,avgWidth, height1);
        frame.add(avg0);
        ta0Label.setBounds(xl,y,widthLabel,height);
        ta0Label.setForeground(Color.white);
        ta0Label.setText("Q1:");
        frame.add(ta0Label);
        ta0.setEditable(false);

        ta1.setBounds(x,y+30,width, height1);
        frame.add(ta1);
        avg1.setBounds(x+400,y+30,avgWidth, height1);
        frame.add(avg1);
        ta1Label.setBounds(xl,y+30,widthLabel,height);
        ta1Label.setForeground(Color.white);
        ta1Label.setText("Q2:");
        frame.add(ta1Label);
        ta1.setEditable(false);

        ta2.setBounds(x,y+60,width, height1);
        frame.add(ta2);
        avg2.setBounds(x+400,y+60,avgWidth, height1);
        frame.add(avg2);
        ta2Label.setBounds(xl,y+60,widthLabel,height);
        ta2Label.setForeground(Color.white);
        ta2Label.setText("Q3:");
        frame.add(ta2Label);
        ta2.setEditable(false);


        ta3.setBounds(x,y+90,width, height1);
        frame.add(ta3);
        avg3.setBounds(x+400,y+90,avgWidth, height1);
        frame.add(avg3);
        ta3Label.setBounds(xl,y+90,widthLabel,height);
        ta3Label.setForeground(Color.white);
        ta3Label.setText("Q4:");
        frame.add(ta3Label);
        ta3.setEditable(false);

        ta4.setBounds(x,y+120,width, height1);
        frame.add(ta4);
        avg4.setBounds(x+400,y+120,avgWidth, height1);
        frame.add(avg4);
        ta4Label.setBounds(xl,y+120,widthLabel,height);
        ta4Label.setForeground(Color.white);
        ta4Label.setText("Q5:");
        frame.add(ta4Label);
        ta4.setEditable(false);

        ta5.setBounds(x,y+150,width, height1);
        frame.add(ta5);
        avg5.setBounds(x+400,y+150,avgWidth, height1);
        frame.add(avg5);
        ta5Label.setBounds(xl,y+150,widthLabel,height);
        ta5Label.setForeground(Color.white);
        ta5Label.setText("Q6:");
        frame.add(ta5Label);
        ta5.setEditable(false);

        ta6.setBounds(x,y+180,width, height1);
        frame.add(ta6);
        avg6.setBounds(x+400,y+180,avgWidth, height1);
        frame.add(avg6);
        ta6Label.setBounds(xl,y+180,widthLabel,height);
        ta6Label.setForeground(Color.white);
        ta6Label.setText("Q7:");
        frame.add(ta6Label);
        ta6.setEditable(false);

        ta7.setBounds(x,y+210,width, height1);
        frame.add(ta7);
        avg7.setBounds(x+400,y+210,avgWidth, height1);
        frame.add(avg7);
        ta7Label.setBounds(xl,y+210,widthLabel,height);
        ta7Label.setForeground(Color.white);
        ta7Label.setText("Q8:");
        frame.add(ta7Label);
        ta7.setEditable(false);

        ta8.setBounds(x,y+240,width, height1);
        frame.add(ta8);
        avg8.setBounds(x+400,y+240,avgWidth, height1);
        frame.add(avg8);
        ta8Label.setBounds(xl,y+240,widthLabel,height);
        ta8Label.setForeground(Color.white);
        ta8Label.setText("Q9:");
        frame.add(ta8Label);
        ta8.setEditable(false);

        ta9.setBounds(x,y+270,width, height1);
        frame.add(ta9);
        avg9.setBounds(x+400,y+270,avgWidth, height1);
        frame.add(avg9);
        ta9Label.setBounds(xl,y+270,widthLabel,height);
        ta9Label.setForeground(Color.white);
        ta9Label.setText("Q10:");
        frame.add(ta9Label);
        ta9.setEditable(false);

        queues.add(ta0);
        queues.add(ta1);
        queues.add(ta2);
        queues.add(ta3);
        queues.add(ta4);
        queues.add(ta5);
        queues.add(ta6);
        queues.add(ta7);
        queues.add(ta8);
        queues.add(ta9);

        frame.add(avg0Label);
        averages.add(avg0);
        averages.add(avg1);
        averages.add(avg2);
        averages.add(avg3);
        averages.add(avg4);
        averages.add(avg5);
        averages.add(avg6);
        averages.add(avg7);
        averages.add(avg8);
        averages.add(avg9);


        frame.setVisible(true);

    }

    /**
     * Adds action event to the Start Button
     * @return No return value
     */
    void addActionEvents(){
        startButton.addActionListener(e -> {
            System.out.println("The start button was pressed");
            try{
                maxNumberOfQueues = qs.getAndCheckInput(numberOfQueues.getText(),"numberOfQueues");
                minArrivalTime = qs.getAndCheckInput(minimumArrivalTime.getText(),"minArrivalTime");
                maxArrivalTime = qs.getAndCheckInput(maximumArrivalTime.getText(),"maxArrivalTime");
                minServiceTime = qs.getAndCheckInput(minimumServiceTime.getText(),"minServiceTime");
                maxServiceTime = qs.getAndCheckInput(maximumServiceTime.getText(),"maxServiceTime");
                simulationIntervalVar = qs.getAndCheckInput(simulationInterval.getText(),"simulationInterval");

                System.out.println("times::::");
                System.out.println(minServiceTime);
                System.out.println(maxServiceTime);
                System.out.println(minArrivalTime);
                System.out.println(maximumArrivalTime);
                System.out.println(simulationInterval);
                System.out.println(maxNumberOfQueues);

            }
            catch(InvalidInputException error){
                JOptionPane.showMessageDialog(frame,error.getMessage());
            }
            qs.setMaxNumberOfQueues(maxNumberOfQueues);
            qs.setMinArrivalTime(minArrivalTime);
            qs.setMaxArrivalTime(maxArrivalTime);
            qs.setMinServiceTime(minServiceTime);
            qs.setMaxServiceTime(maxServiceTime);
            qs.setSimulationInterval(simulationIntervalVar);
            qs.start();

        });
    }

    /**
     * Simply prints the formatted string version o the clients present in the queue
     * @param clientQueueList
     * @return No return value
     */
    public void displayQueues(List<Queue> clientQueueList){
        int i = 0;
        for(Queue q : clientQueueList){
            queues.get(i).setText(q.toString());
            System.out.println(q.toString());
            i++;
        }
    }

    /**
     * Prints the average waiting time for every queue used
     * @param clientQueueList
     * @return No return value
     */

    public void displayAverages(List<Queue> clientQueueList) {
        int i = 0;
        for(Queue q : clientQueueList){
            averages.get(i).setText(q.getAverageWaitingTime()+"");
            System.out.println(q.getAverageWaitingTime());
            i++;
        }

    }

}
