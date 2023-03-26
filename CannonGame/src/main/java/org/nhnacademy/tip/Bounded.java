package org.nhnacademy.tip;

/**
 * 움직일 수 있는 물체중 이동이 가능한 허용 공간을 지정하거나, 이동이 허용되지 않는 제약 공간을 제외시켜야 하는 타입을 나타낸다.
 * 허용 공간을 벗어나거나 제약 공간에 들어가는 경우, 튕겨져 나오는 등의 추가적인 조치를 수행한다.
 */
public interface Bounded extends Movable {
    /**
     * 허용 공간 설정. 미설정시 제약 없음.
     *
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public void setBounds(int x, int y, int width, int height);

    /**
     * 제약 공간 등록. 제약 공간은 하나이상 구성 가능
     *
     * @param bounds
     */

    public void addExcludedBounds(Bounds bounds);

    /**
     * 충돌(허용 공간을 벗어나거나, 제약 공간에 들어간 감) 감지 이벤트 수신자 설정.
     * @param listener
     */
    public void addCollisionEventListener(CollisionEventListener listener);
}
