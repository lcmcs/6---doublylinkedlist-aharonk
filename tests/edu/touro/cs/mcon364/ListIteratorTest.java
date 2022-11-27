package edu.touro.cs.mcon364;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ListIteratorTest {
    DLinkedList dll;
    DLinkedList.MyLIterator mIter;

    @BeforeEach
    void create() {
        dll = new DLinkedList();
        mIter = dll.new MyLIterator(0);
    }

    @Test
    void createFail() {
        assertThrows(IndexOutOfBoundsException.class, () -> dll.new MyLIterator(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> dll.new MyLIterator(1));
    }

    @Test
    void hasPrevAndPrev() {
        assertFalse(mIter.hasPrevious());

        mIter.add("a");

        assertTrue(mIter.hasPrevious());
        assertEquals("a", mIter.previous());
    }

    @Test
    void hasNextAndNext() {
        assertFalse(mIter.hasNext());

        mIter.add("a");
        mIter.previous();

        assertTrue(mIter.hasNext());
        assertEquals("a", mIter.next());
    }

    @Test
    void removeFail() {
        mIter.add("a");
        assertThrows(IllegalStateException.class, () -> mIter.remove());

        mIter.previous();

        mIter.remove();
        assertThrows(IllegalStateException.class, () -> mIter.remove());
    }

    @Test
    void remove() {
        mIter.add("a");
        mIter.previous();

        mIter.remove();
        assertEquals(0, dll.size());
    }

    @Test
    void setFail() {
        mIter.add("a");
        assertThrows(IllegalStateException.class, () -> mIter.set("z"));

        mIter.previous();
        mIter.remove();
        assertThrows(IllegalStateException.class, () -> mIter.set("z"));
    }

    @Test
    void set() {
        mIter.add("a");
        mIter.previous();

        mIter.set("z");
        assertEquals("z", mIter.next());
    }

    @Test
    void add() {
        mIter.add("a");
        mIter.add("b");
        mIter.previous();
        mIter.add("c"); // {a,c^b}, no lastReturned

        assertEquals("c", mIter.previous()); // {a>c,b}
        assertEquals("a", mIter.previous()); // {>a,c,b}
        assertEquals("a", mIter.next()); //     {a<c,b}
        assertEquals("c", mIter.next()); //     {a,c<b}
        assertEquals("b", mIter.next()); //     {a,c,b<}
        assertEquals("b", mIter.previous()); // {a,c>b}
        assertEquals("c", mIter.previous()); // {a>c,b}

        assertEquals(3, dll.size());
    }

    @Test
    void indices() {
        mIter.add("a");
        assertEquals(0, mIter.previousIndex());
        assertEquals(1, mIter.nextIndex());
        mIter.add("b");
        assertEquals(1, mIter.previousIndex());
        assertEquals(2, mIter.nextIndex());
        mIter.previous();
        mIter.add("c");
        assertEquals(1, mIter.previousIndex());
        assertEquals(2, mIter.nextIndex());

        //{a,c,b}

        mIter.previous();
        assertEquals(0, mIter.previousIndex());
        assertEquals(1, mIter.nextIndex());
        mIter.remove();
        assertEquals(0, mIter.previousIndex());
        assertEquals(1, mIter.nextIndex());

        mIter.next();
        assertEquals(1, mIter.previousIndex());
        assertEquals(2, mIter.nextIndex());
        mIter.remove();
        assertEquals(0, mIter.previousIndex());
        assertEquals(1, mIter.nextIndex());
    }
}
