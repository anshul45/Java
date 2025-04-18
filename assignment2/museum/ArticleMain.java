package com.museum;

import java.time.LocalDate;
import java.util.Scanner;

import com.museum.entity.Article;
import com.museum.entity.Category;
import com.museum.service.ArticleService;

public class ArticleMain {
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		int ch;
		
		ArticleService service = null;
		
		try {
			service = new ArticleService();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		while (flag) {
			System.out.println("------Menu-------");
			System.out
					.println("1.for show all Articles. \n2.for get Article by id \n3.to add Article \n4.to delete article by id \n5.to update article by id \n0.for exit");
			ch = sc.nextInt();
			switch (ch) {
			case 0: {
				flag = false;
				break;
			}
			case 1: {
				try {
					service.displayAllArticle();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				break;
			}
			case 2: {
				System.out.println("enter id");
				try {
					service.displayArticle(sc.nextInt());
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				break;
			}
			case 3: {
				System.out.println("enter name, category(PAINTING,SCULPTURE, ARTIFACT),date, creator name");
				try {
					service.addArticle(sc.next(), Category.valueOf(sc.next()), LocalDate.parse(sc.next()), sc.next());
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				break;
			}
			case 4: {
				System.out.println("enter id");
				try {
					service.deleteArticle(sc.nextInt());
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				break;
			}
			case 5: {
				System.out.println("enter id, name, category(PAINTING,SCULPTURE, ARTIFACT),date, creator name");
				try {
					service.updateArticle(sc.nextInt(), sc.next(), Category.valueOf(sc.next()), LocalDate.parse(sc.next()), sc.next());
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
			
			
			default:
				System.err.println("Enter valid choice");
			}
		}
		sc.close();
	};
}
