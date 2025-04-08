import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double lastResult = 0;
        boolean hasPreviousResult = false;
        int operationChoice;

        LinkedList<String> historyList = new LinkedList<>();

        do {
            // Hiển thị menu
            System.out.println("\nChọn phép toán:");
            System.out.println("1. + : Cộng");
            System.out.println("2. - : Trừ");
            System.out.println("3. * : Nhân");
            System.out.println("4. / : Chia");
            System.out.println("5. % : Chia lấy dư");
            System.out.println("6. √ : Căn bậc 2");
            System.out.println("7. ^ : Lũy thừa");
            System.out.println("8. Xem 5 phép toán gần nhất");
            System.out.println("0. Thoát");
            System.out.print("Nhập lựa chọn: ");
            operationChoice = sc.nextInt();

            if (operationChoice < 0 || operationChoice > 8) {
                System.out.println("Lỗi: Phép toán không hợp lệ! Vui lòng chọn từ 0 đến 8.");
                continue;
            }

            if (operationChoice == 0) {
                System.out.println("Chương trình kết thúc.");
                break;
            }

            if (operationChoice == 8) {
                System.out.println("5 phép toán gần nhất:");
                if (historyList.isEmpty()) {
                    System.out.println("Chưa có phép toán nào được thực hiện.");
                } else {
                    for (String result : historyList) {
                        System.out.println(result);
                    }
                }
                continue;
            }

            double firstNumber;
            if (hasPreviousResult) {
                System.out.println("Bạn có muốn sử dụng kết quả trước đó (" + lastResult + ") không?");
                System.out.println("1. Có");
                System.out.println("2. Nhập số mới");
                System.out.print("Lựa chọn của bạn: ");
                int usePrevious = sc.nextInt();

                if (usePrevious == 1) {
                    firstNumber = lastResult;
                    System.out.println("Số thứ nhất: " + firstNumber);
                } else {
                    System.out.print("Nhập số thứ nhất: ");
                    firstNumber = sc.nextDouble();
                }
            } else {
                System.out.print("Nhập số thứ nhất: ");
                firstNumber = sc.nextDouble();
            }

            double secondNumber = 0;
            if (operationChoice == 7) {
                System.out.print("Nhập số mũ: ");
                secondNumber = sc.nextDouble();
            } else {
                System.out.print("Nhập số thứ 2: ");
                secondNumber = sc.nextDouble();
            }

            boolean isValidOperation = true;
            String operator = "";

            switch (operationChoice) {
                case 1:
                    lastResult = firstNumber + secondNumber;
                    operator = "+";
                    break;
                case 2:
                    lastResult = firstNumber - secondNumber;
                    operator = "-";
                    break;
                case 3:
                    lastResult = firstNumber * secondNumber;
                    operator = "*";
                    break;
                case 4:
                    if (secondNumber != 0) {
                        lastResult = firstNumber / secondNumber;
                        operator = "/";
                    } else {
                        System.out.println("Lỗi: Không thể chia cho 0!");
                        isValidOperation = false;
                    }
                    break;
                case 5:
                    if (secondNumber != 0) {
                        lastResult = firstNumber % secondNumber;
                        operator = "%";
                    } else {
                        System.out.println("Lỗi: Không thể chia cho 0!");
                        isValidOperation = false;
                    }
                    break;
                case 6:
                    if (firstNumber >= 0) {
                        lastResult = Math.sqrt(firstNumber);
                        operator = "√";
                    } else {
                        System.out.println("Lỗi: Không thể căn bậc hai số âm!");
                        isValidOperation = false;
                    }
                    break;
                case 7:
                    lastResult = Math.pow(firstNumber, secondNumber);
                    operator = "^";
                    break;
            }

            if (isValidOperation) {
                String result;
                if (operationChoice == 6) {
                    result = operator + firstNumber + " = " + lastResult;
                } else {
                    result = firstNumber + " " + operator + " " + secondNumber + " = " + lastResult;
                }

                System.out.println("Kết quả: " + result);

                historyList.add(result);
                if (historyList.size() > 5) {
                    historyList.removeFirst();
                }

                hasPreviousResult = true;
            }

        } while (true);

        sc.close();
    }
}
