package com.ssafy.hw2011;

import java.util.Arrays;

import bookmanage.Book;

public class BookManagerImpl implements IBookManager {
	
	
    private static final int MAX_SIZE = 100;
    private Book[] bks = new Book[MAX_SIZE];
    private int size = 0;
    
    // 생성자 private
    private static BookManagerImpl IBM = new BookManagerImpl();
    
    private BookManagerImpl() { }
    
    public static BookManagerImpl getInstance() {
    	
		return IBM;
	}	
    
	@Override
	public void add(Book book) {
		bks[size++] = book;
	}
	
	@Override
	public void remove(String isbn) {
		//삭제한 요소의 인덱스 찾기
		int deleteIdx = -1;
		for(int i=0; i<size; i++) {
			if(bks[i].getIsbn().equals(isbn)) {
				deleteIdx = i;
				break;
			}
		}
		//삭제한 요소 위치와 마지막 요소 자리바꾸기
		bks[deleteIdx] = bks[size-1];
		bks[size-1] = null;
		size--;
	}
	
	@Override
	public Book[] getList() {
		return Arrays.copyOf(bks, size);
	}
	
	@Override
	public Book searchByIsbn(String isbn) {
		for(int i=0; i<size; i++) {
			// == 쓰면안됨
			if(bks[i].getIsbn().equals(isbn)) {
				//조회해서 찾음
				return bks[i];
			}
		}
		//조회했는데 없음
		return null;
	}
	
	@Override
	public Book[] searchByTitle(String title) {
		Book[] t_bks = new Book[MAX_SIZE];
		int t_size = 0;
		for(int i=0; i<size; i++) {
			if(bks[i].getTitle().contains(title)) {
				t_bks[t_size++] = bks[i];
			}
		}
		return Arrays.copyOf(t_bks, t_size);
		
	}
	
	@Override
	public Magazine[] getMagazines() {
		Magazine[] g_mgzs = new Magazine[MAX_SIZE];
		int m_size = 0;
		for(int i=0; i<size; i++) {
			if(bks[i] instanceof Magazine) {
				g_mgzs[m_size++] = (Magazine) bks[i];
			}
		}
		return Arrays.copyOf(g_mgzs, m_size);
		
	}
	
	@Override
	public Book[] getBooks() {
		Book[] g_bks = new Book[MAX_SIZE];
		int g_size = 0;
		for(int i=0; i<size; i++) {
			if(bks[i] instanceof Book && !(bks[i] instanceof Magazine)) {
				g_bks[g_size++] = bks[i];
			}
		}
		return Arrays.copyOf(g_bks, g_size);
	}
	
	@Override
	public int getTotalPrice() {
		int result = 0;
		for(int i=0; i<size; i++) {
			result += bks[i].getPrice();
		}
		return result;
	}
	
	@Override
	public double getPriceAvg() {
		int result = 0;
		for(int i=0; i<size; i++) {
			result += bks[i].getPrice();
		}
		return result/size;
	}
}