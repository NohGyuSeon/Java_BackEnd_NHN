package week01.day03.homework1.code;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 생산자-소비자 솔루션을 수정하라
 * 문제 1
 * 마트에서는 N개의 품목 매장이 있다.
 * 생산자는 N개의 품목을 납품할 수 있다.
 * 마트에서는 생산자가 품목을 납품하기 전까지는 어떤 품목인지 알 수 없다.
 * 소비자는 품목별로 매장을 이용할 수 있다. 즉, 여러 사람이 각기 다른 품목을 구매할 경우 동시 구매가 가능하다.
 * 문제 2
 * 프로그램에서 사용되는 세마포어를 구현하여 적용하라.
 */
public class Mart {
    private final MySemaphore tickets;  // 직접 구현한 세마포어
    private final int[] products = new int[5]; // 품목 매장 배열
    private final List<Integer> buyingProducts; // 구매중인 상품들의 리스트

    /**
     * 마트 생성자
     */
    public Mart() {
        tickets = new MySemaphore(5);
        buyingProducts = new ArrayList<>();
    }

    public void enter() throws InterruptedException {
        try {
            this.tickets.acquire();
            out.println(Thread.currentThread().getName() + " 입장");
        } catch (InterruptedException e) {
            throw e;
        }
    }

    /**
     * @return 품목 매장 수
     */
    public int getProductsSize() {
        return products.length;
    }

    /**
     * @param productIndex 품목 매장 인덱스
     */
    // 동시 구매는 가능, 동일한 품목은 구매 불가
    public void buy(int productIndex) {
        while (productsCount() == 0 || products[productIndex] == 0 || isProductContains(productIndex)) {
            try {
                out.println(Thread.currentThread().getName() + ": " + (productIndex+1) + "번째 품목 수량이 없거나 중복이므로 대기");
                Thread.sleep(1000);
                if(productsCount() >= 50) {
                    notifyAll();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            if (isProductContains(productIndex)) {
                out.println(Thread.currentThread().getName() + ": " + (productIndex + 1) + "번 가게에서 중복 구매 불가");
            } else {
                buyingProducts.add(productIndex);
                out.println(
                Thread.currentThread().getName() + ": " + (productIndex + 1) + "번 가게 상품 구매 완료");
                --products[productIndex];
                
                out.println("구매 상품 초기화 완료");
                for (int i = 0; i < products.length; i++) {
                    out.println((i+1) + "번 가게 상품 재고: " + products[i]);
                }
            }
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void exit() throws InterruptedException {
        tickets.release();
        out.println(Thread.currentThread().getName() + " 퇴장");
    }

    /**
     * @return 마트 내 모든 품목의 개수 반환
     */
    public int productsCount() {
        int initalProductCount = 0;
        for(int product: products) {
            initalProductCount += product;
        }

        return initalProductCount;
    }

    /**
     * @param productIndex 품목 매장 인덱스
     * @return 중복 매장에 포함되는지 검사
     */
    public boolean isProductContains(int productIndex) {
        return buyingProducts.contains(productIndex);
    }

    /**
     * 품목 매장 당 10의 품목 전시 가능 -> 50개 이상 품목 마트에 있을 시 납품 대기 상태...
     */
    public synchronized void sell() {
        while (productsCount() >= 10 * products.length) {
            try {
                out.println("납품 대기 중입니다.");
                wait();
                Thread.sleep(100);
            } catch (InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }
        }

        // 랜덤한 N개의 상품을 납품
        out.println("납품 시작");
        for (int i = 0; i < 5; i++) {
            ++products[ThreadLocalRandom.current().nextInt(0, products.length)];
            if(productsCount() >= 50) {
                break;
            }
        }
        for (int i = 0; i < products.length; i++) {
            out.println((i+1) + "번째 가게 품목의 재고: " + products[i]);
        }
        out.println("납품 완료");
        buyingProducts.clear();
        notify();
    }
}
