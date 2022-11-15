package edu.touro.cs.mcon364;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DLinkedListTest {

    private static final DLinkedList DLL = new DLinkedList();
    private static List<String> OTHER_LIST;

    @Test
    @Order(-1)
    void listIteratorFail() {
        assertThrows(IndexOutOfBoundsException.class, () -> DLL.listIterator(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> DLL.listIterator(1));
    }

    @Test
    @Order(0)
    void empty() {
        assertTrue(DLL.isEmpty());
    }

    @Test
    @Order(1)
    void add() {
        assertTrue(DLL.add("a"));
        assertTrue(DLL.add("b"));
    }

    @Test
    @Order(2)
    void size() {
        assertEquals(2, DLL.size());
    }

    @Test
    @Order(2)
    void notEmpty() {
        assertFalse(DLL.isEmpty());
    }

    @Test
    @Order(3)
    void contains() {
        assertTrue(DLL.contains("a"));
        assertTrue(DLL.contains("b"));
        assertFalse(DLL.contains("c"));
        assertFalse(DLL.contains(5));
    }

    @Test
    @Order(4)
    void addAt() {
        DLL.add(1, "c");
        assertEquals("c", DLL.get(1));
        assertEquals("b", DLL.get(2));

        DLL.add(3, "d");
        assertEquals("d", DLL.get(3));
        assertEquals("b", DLL.get(2));
    }

    @Test
    @Order(4)
    void addAtFail() {
        assertThrows(IndexOutOfBoundsException.class, () -> DLL.add(7, "e"));
    }

    @Test
    @Order(5)
    void get() {
        assertEquals("a", DLL.get(0));
        assertEquals("c", DLL.get(1));
    }

    @Test
    @Order(5)
    void getFail() {
        assertThrows(IndexOutOfBoundsException.class, () -> DLL.get(7));
    }

    @Test
    @Order(6)
    void set() {
        assertEquals("a", DLL.set(0, "f"));
        assertEquals("f", DLL.set(0, "a"));
    }

    @Test
    @Order(6)
    void setFail() {
        assertThrows(IndexOutOfBoundsException.class, () -> DLL.set(7, "g"));
    }

    @Test
    @Order(7)
    void removeIndex() {
        assertEquals("d", DLL.remove(3));
        assertEquals(3, DLL.size());
    }

    @Test
    @Order(7)
    void removeIndexFail() {
        assertThrows(IndexOutOfBoundsException.class, () -> DLL.remove(4));
    }

    @Test
    @Order(8)
    void toArray() {
        assertArrayEquals(new String[]{"a", "c", "b"}, DLL.toArray());
    }

    @Test
    @Order(8)
    void toArrayBackDoor() {
        Object[] a = DLL.toArray();
        a[0] = "z";
        assertEquals("a", DLL.get(0));
        assertFalse(DLL.contains("z"));
    }

    @Test
    @Order(9)
    void indexOf() {
        DLL.add("c");
        assertEquals(1, DLL.indexOf("c"));
        assertEquals(-1, DLL.indexOf("z"));
    }

    @Test
    @Order(10)
    void lastIndexOf() {
        assertEquals(3, DLL.lastIndexOf("c"));
        assertEquals(-1, DLL.lastIndexOf("z"));
    }

    @Test
    @Order(11)
    void removeObject() {
        assertTrue(DLL.remove("c"));
        assertEquals("a", DLL.get(0));
        assertEquals("b", DLL.get(1));
        assertEquals("c", DLL.get(2));
        assertEquals(3, DLL.size());

        assertFalse(DLL.remove("z"));
    }

    @Test
    @Order(12)
    void addAll() {
        OTHER_LIST = Arrays.asList("x", "y", "z");
        assertTrue(DLL.addAll(OTHER_LIST));
        assertEquals(6, DLL.size());
    }

    @Test
    @Order(12)
    void addAllFail() {
        assertThrows(NullPointerException.class, () -> DLL.addAll(null));
    }

    @Test
    @Order(13)
    void containsAll() {
        assertTrue(DLL.containsAll(OTHER_LIST));
    }

    @Test
    @Order(13)
    void containsAllFail() {
        assertThrows(NullPointerException.class, () -> DLL.containsAll(null));
    }

    @Test
    @Order(14)
    void removeAll() {
        assertTrue(DLL.removeAll(OTHER_LIST));
        assertEquals("a", DLL.get(0));
        assertEquals("b", DLL.get(1));
        assertEquals("c", DLL.get(2));
        assertEquals(3, DLL.size());
    }

    @Test
    @Order(14)
    void removeAllFail() {
        assertThrows(NullPointerException.class, () -> DLL.removeAll(null));
    }

    @Test
    @Order(15)
    void addAllIndex() {
        assertTrue(DLL.addAll(1, OTHER_LIST));
        assertEquals(6, DLL.size());
    }

    @Test
    @Order(15)
    void addAllIndexFail() {
        assertThrows(NullPointerException.class, () -> DLL.addAll(1, null));
        assertThrows(IndexOutOfBoundsException.class, () -> DLL.addAll(10, OTHER_LIST));
        assertThrows(IndexOutOfBoundsException.class, () -> DLL.addAll(-1, OTHER_LIST));
    }

    @Test
    @Order(16)
    void retainAll() {
        assertTrue(DLL.retainAll(OTHER_LIST));
        assertEquals(3, DLL.size());
    }

    @Test
    @Order(16)
    void retainAllFail() {
        assertThrows(NullPointerException.class, () -> DLL.retainAll(null));
    }

    @Test
    @Order(17)
    void iterator() {
        int i = 0;
        for (String s : DLL) {
            assertEquals(OTHER_LIST.get(i++), s);
        }
    }

    @Test
    @Order(17)
    void iteratorFail() {
        Iterator<String> it = DLL.iterator();
        it.next();
        it.next();
        it.next();
        assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    @Order(18)
    void clear() {
        DLL.clear();
        assertTrue(DLL.isEmpty());
        assertEquals(0, DLL.size());
    }
}