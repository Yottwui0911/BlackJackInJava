package main;

import java.util.Scanner;

import models.BlackJackController;

public class Main {

	public static void main(String[] args) {
		System.out.println("ようこそBlackJackへ\nお名前を入力してください。\n");
        Scanner sc1 = new Scanner(System.in);
        String name = sc1.nextLine();

		BlackJackController c = new BlackJackController(name);
		c.Command("help");
		c.Command("hand");
		c.Command("dhand");

		while(!c.isFinish()) {
			System.out.println("コマンドを入力してください。");
	        String command = sc1.nextLine();
	        c.Command(command);
		}

		c.WinningMsg();
		sc1.close();
	}
}
