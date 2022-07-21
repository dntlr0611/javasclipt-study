package TEST;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Test03 extends JFrame{
	private JTextField inputSpace;
	// 숫자 지정.
	private String num = "";
	// 연산 기호 구분해서 담음
	private ArrayList<String> Start = new ArrayList<>();
	public Test03(){
		// 계산기에 버튼을 붙임.
		setLayout(null);
		// 빈공간의 JtextField 생성
		inputSpace = new JTextField();
		// 편집가능 여부: 불가능 = 버튼만 사용
		inputSpace.setEditable(false);
		// 배경색 설정
		inputSpace.setBackground(Color.LIGHT_GRAY);
		// 정렬 위치
		inputSpace.setHorizontalAlignment(JTextField.RIGHT);
		// 글씨체 설정
		inputSpace.setFont(new Font("monospace", Font.BOLD, 50));
		// 위치와 크기
		inputSpace.setBounds(8, 10, 270, 70);


		//버튼을 만들 패널
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));
		// 위치와 크기 설정
		buttonPanel.setBounds(8, 90, 270, 235);
		
		// 계산기 버튼의 글자를 차례대로 배열에 저장
		String button_names[] = {"C", "/", "*", "=", "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "0"};
		// 버튼들의 배열
		JButton buttons[] = new JButton[button_names.length];

		// 배열을 이용하여 버튼 생성
		for(int i=0; i < button_names.length; i++){
			buttons[i] = new JButton(button_names[i]);
			// 글씨체
			buttons[i].setFont(new Font("monospace", Font.BOLD, 20));
			// 버튼의 색 지정
			if(button_names[i] == "AC"){
				buttons[i].setBackground(Color.blue);
			}else if((4<=i && i<=6) || (8<= i && i<=10) || (12<= i && i<=14)){
				buttons[i].setBackground(Color.BLACK);
			}else{
				buttons[i].setBackground(Color.GRAY);
			}
			// 글씨색 지정
			buttons[i].setForeground(Color.WHITE);
			// 테두리 없애기
			buttons[i].setBorderPainted(false);
			// 버튼 이벤트 추가
			buttons[i].addActionListener(new PadActionListener());
			// 버튼들을 패널에 추가
			buttonPanel.add(buttons[i]);
		}
		add(inputSpace);
		add(buttonPanel);

		// 창의 제목, 사이즈, 보이기 여부 등을 지정
		setTitle("계산기");
		setVisible(true);
		setSize(300,370);
		// 화면을 가운데 띄움
		setLocationRelativeTo(null);
		// 사이즈 조절 불가능
		setResizable(false);
		// 창을 닫을 때 실행 중인 프로그램도 같이 종료되도록 함.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	// 이벤트 추가.
	class PadActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String operation = e.getActionCommand();
			// AC 눌렸을 경우 Init
			if(operation.equals("C")){
				inputSpace.setText("");
			}else if(operation.equals("=")){
				// = 계산값 계산.
				String result = Double.toString(calculate(inputSpace.getText()));
				inputSpace.setText("" + result);
				num = "";
			}else{
				// 나머지 버튼 계산식 추가.
				inputSpace.setText(inputSpace.getText() + e.getActionCommand());
			}
		}
	}

	private void fullTextParsing(String inputText){
		Start.clear();

		// 계산식의 글자를 하나하나 거쳐감.
		for(int i=0; i< inputText.length(); i++){
			char ch = inputText.charAt(i);
			// 연산기호가 나오면 ArrayList에 추가되고 초기화
			if(ch == '-' || ch == '+' || ch == '*' || ch == '-'){
				// 연산기호를 만났으면 숫자 추가.
				Start.add(num);
				// num 초기화
				num = "";
				// 연산기호를 ArrayList에 추가
				Start.add(ch + "");
			}else{
				num = num + ch;
			}
		}
		Start.add(num);
	}
	//계산 기능
	public double calculate(String inputText) {
		fullTextParsing(inputText);
		
		//위의 메소드를 실행하면 ArrayList에 숫자와 연산 기호가 담김
		double prev = 0;
		double current = 0;
		//연산 기호에 대한 처리를 위한 변수
		String mode = "";
		

		//+일경우 add, -일경우 sub, x일경우 mul, /일경우 div
		for (String s : Start) {
			if (s.equals("+")) {
				mode = "add";
			} else if (s.equals("-")) {
				mode = "sub";
			}  
			else if (s.equals("*")) {
				mode = "mul";
			}  
			else if (s.equals("÷")) {
				mode = "div";
			}  else {
				//숫자일 경우 문자열을 Double로 형변환
				current = Double.parseDouble(s);
				
				//mode값에 따라 처리, prev는 계속 계산값이 갱신됨
				if (mode.equals("add")) {
					prev += current;

				} else if (mode.equals("sub")) {
					prev -= current;
				} 
				else if (mode.equals("mul")) {
					prev *= current;
				} 
				else if (mode.equals("div")) {
					prev /= current;

				} else {
					prev = current;
					
				}
			}
		}
		//계산값 prev 반환	
		return prev;
	}
	public static void main(String[] args){
		new Test03();
	}
}