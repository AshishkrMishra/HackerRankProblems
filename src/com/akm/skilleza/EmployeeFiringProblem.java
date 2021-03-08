package com.akm.skilleza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Ashish Kr Mishra
 * @email (er.ashish.kr.mishra@gmail.com)
 */
class EmployeeFiringProblem {
	public static int T = -1;
	// Store All Different TestCase and Input
	public static List<Input> inputArrays = new ArrayList<Input>();

	/**
	 * Input - Class Represent An Indiviual Testcase
	 * 
	 * @author Ashish Kr Mishra
	 * @email (er.ashish.kr.mishra@gmail.com)
	 */
	static class Input {
		// E - NO OF EMPLOYEE
		public int E = -1;
		// C - NO OF COMPONENTS
		public int C = -1;
		// D - DISTNACE DEFINED WRT QUITER
		public int D = -1;
		// QUITER'S COMPONENTS
		public Integer[] quiterEmployeesComponents = null;
		// ALL EMPLOYESS COMPONENTS
		public List<Integer[]> employeeDatas = new ArrayList<Integer[]>();

		public int getE() {
			return E;
		}

		public void setE(int e) {
			E = e;
		}

		public int getC() {
			return C;
		}

		public void setC(int c) {
			C = c;
		}

		public int getD() {
			return D;
		}

		public void setD(int d) {
			D = d;
		}

		public Integer[] getQuiterEmployeesComponents() {
			return quiterEmployeesComponents;
		}

		public void setQuiterEmployeesComponents(Integer[] quiterEmployeesComponents) {
			this.quiterEmployeesComponents = quiterEmployeesComponents;
		}

		public List<Integer[]> getEmployeeDatas() {
			return employeeDatas;
		}

		public void setEmployeeDatas(List<Integer[]> employeeDatas) {
			this.employeeDatas = employeeDatas;
		}

	}

	public static void main(String[] args) {

		try {
			process();
		} catch (Exception e) {
			System.err.println("Failed While Processing due to :" + e.getMessage());
		}

	}

	public static void process() throws NumberFormatException, IOException {
		ReadInput();
		for (Input input : inputArrays) {
			findNoOfEmployeeCanBeFired(input);
		}
	}

	/**
	 * findNoOfEmployeeCanBeFired
	 */
	public static void findNoOfEmployeeCanBeFired(Input input) {
		int noOfEmployeeToFire = 0;
		if (Objects.isNull(input.getQuiterEmployeesComponents())) {
			System.out.println(noOfEmployeeToFire);
			return;
		}
		for (Integer[] employeeData : input.getEmployeeDatas()) {
			int currentEmployeeDistance = 0;
			for (int componentNo = 0; componentNo < input.getC(); componentNo++) {
				currentEmployeeDistance += input
						.getQuiterEmployeesComponents()[componentNo] == employeeData[componentNo] ? 0 : 1;
			}
			if (currentEmployeeDistance <= input.getD()) {
				noOfEmployeeToFire++;
			}
		}
		System.out.println(noOfEmployeeToFire);
	}

	/**
	 * printInputData - This Method Can be used to print Input Data to Test Parsing
	 * Logic
	 */
	public static void printInputData() {
		System.out.println("#######################");
		System.out.println(T);
		for (Input input : inputArrays) {
			printInputDataUtil(input);
		}

	}

	/**
	 * printInputDataUtil - Util method to print indivial test-case
	 */
	private static void printInputDataUtil(Input input) {
		System.out.println("#######################");
		System.out.println(T);
		System.out.printf("%d %d %d \n", input.E, input.C, input.D);
		for (Integer[] employeeData : input.employeeDatas) {
			for (int val : employeeData) {
				System.out.print(val + " ");
			}
			System.out.println();
		}
	}

	/**
	 * ReadInput -read Input data from console
	 * 
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void ReadInput() throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.valueOf(reader.readLine());
		for (int testCaseNo = 1; testCaseNo <= T; testCaseNo++) {
			inputArrays.add(ReadInputUtil(reader));
		}
	}

	/**
	 * ReadInputUtil -ultiles method to read input
	 * 
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static Input ReadInputUtil(BufferedReader reader) throws NumberFormatException, IOException {

		Input input = new Input();
		Integer[] ecdValues = getLineToArray(reader.readLine(), 3);
		input.E = ecdValues[0];
		input.C = ecdValues[1];
		input.D = ecdValues[2];
		for (int i = 0; i <= input.E; i++) {
			if (i == 0) {
				input.quiterEmployeesComponents = getLineToArray(reader.readLine(), input.C);
			} else {
				input.employeeDatas.add(getLineToArray(reader.readLine(), input.C));
			}

		}
		return input;

	}

	/**
	 * getLineToArray - Util method convert String from separated integer to array
	 * 
	 * @param line
	 * @param noOfElement
	 * @return
	 */
	public static Integer[] getLineToArray(String line, int noOfElement) {
		Integer[] result = null;
		if (Objects.isNull(line) || line.isEmpty()) {
			return result;
		}
		result = new Integer[noOfElement];
		String[] input = line.split("\\s+");
		for (int index = 0; index < noOfElement; index++) {
			result[index] = Integer.valueOf(input[index]);
		}
		return result;
	}
}
