package edu.touro.cs.mcon364;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DLinkedListTest {

    private DLinkedList DLL;
    private List<String> OTHER_LIST;

    @BeforeEach
    void create() {
        DLL = new DLinkedList();
    }

    @Test
    void listIteratorFail() {
        assertThrows(IndexOutOfBoundsException.class, () -> DLL.listIterator(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> DLL.listIterator(1));
    }

    @Test
    void empty() {
        assertTrue(DLL.isEmpty());
    }

    @Test
    void add() {
        assertTrue(DLL.add("a"));
        assertTrue(DLL.add("b"));
    }

    @Test
    void size() {
        DLL.add("a");
        DLL.add("b");

        assertEquals(2, DLL.size());
    }

    @Test
    void notEmpty() {
        DLL.add("a");

        assertFalse(DLL.isEmpty());
    }

    @Test
    void contains() {
        DLL.add("a");
        DLL.add("b");

        assertTrue(DLL.contains("a"));
        assertTrue(DLL.contains("b"));
        assertFalse(DLL.contains("c"));
        assertFalse(DLL.contains(5));
    }

    @Test
    void addAt() {
        DLL.add("a");
        DLL.add("b");

        DLL.add(1, "c");
        assertEquals("c", DLL.get(1));
        assertEquals("b", DLL.get(2));

        DLL.add(3, "d");
        assertEquals("d", DLL.get(3));
        assertEquals("b", DLL.get(2));
    }

    @Test
    void addAtFail() {
        assertThrows(IndexOutOfBoundsException.class, () -> DLL.add(7, "e"));
    }

    @Test
    void get() {
        DLL.add("a");
        DLL.add("b");
        DLL.add(1, "c");
        DLL.add(3, "d");

        assertEquals("a", DLL.get(0));
        assertEquals("c", DLL.get(1));
    }

    @Test
    void getFail() {
        assertThrows(IndexOutOfBoundsException.class, () -> DLL.get(7));
    }

    @Test
    void set() {
        DLL.add("a");

        assertEquals("a", DLL.set(0, "f"));
        assertEquals("f", DLL.set(0, "a"));
    }

    @Test
    void setFail() {
        assertThrows(IndexOutOfBoundsException.class, () -> DLL.set(7, "g"));
    }

    @Test
    void removeIndex() {
        DLL.add("a");
        DLL.add("b");
        DLL.add(1, "c");
        DLL.add(3, "d");

        assertEquals("d", DLL.remove(3));
        assertEquals(3, DLL.size());
    }

    @Test
    void removeIndexFail() {
        DLL.add("a");
        DLL.add("b");
        DLL.add(1, "c");
        DLL.add(3, "d");

        DLL.remove(3);

        assertThrows(IndexOutOfBoundsException.class, () -> DLL.remove(4));
    }

    @Test
    void toArray() {
        DLL.add("a");
        DLL.add("b");
        DLL.add(1, "c");

        assertArrayEquals(new String[]{"a", "c", "b"}, DLL.toArray());
    }

    @Test
    void toArrayBackDoor() {
        DLL.add("a");
        DLL.add("b");
        DLL.add(1, "c");

        Object[] a = DLL.toArray();
        a[0] = "z";
        assertEquals("a", DLL.get(0));
        assertFalse(DLL.contains("z"));
    }

    @Test
    void indexOf() {
        DLL.add("a");
        DLL.add("b");
        DLL.add(1, "c");
        DLL.add("c");

        assertEquals(1, DLL.indexOf("c"));
        assertEquals(-1, DLL.indexOf("z"));
    }

    @Test
    void lastIndexOf() {
        DLL.add("a");
        DLL.add("b");
        DLL.add(1, "c");
        DLL.add("c");

        assertEquals(3, DLL.lastIndexOf("c"));
        assertEquals(-1, DLL.lastIndexOf("z"));
    }

    @Test
    void removeObject() {
        DLL.add("a");
        DLL.add("b");
        DLL.add(1, "c");
        DLL.add("c");

        assertTrue(DLL.remove("c"));
        assertEquals("a", DLL.get(0));
        assertEquals("b", DLL.get(1));
        assertEquals("c", DLL.get(2));
        assertEquals(3, DLL.size());

        assertFalse(DLL.remove("z"));
    }

    @Test
    void addAll() {
        DLL.add("a");
        DLL.add("b");
        DLL.add("c");

        OTHER_LIST = Arrays.asList("x", "y", "z");
        assertTrue(DLL.addAll(OTHER_LIST));
        assertEquals(6, DLL.size());
    }

    @Test
    void addAllFail() {
        assertThrows(NullPointerException.class, () -> DLL.addAll(null));
    }

    @Test
    void containsAll() {
        DLL.add("a");
        DLL.add("b");
        DLL.add("c");

        OTHER_LIST = Arrays.asList("x", "y", "z");
        DLL.addAll(OTHER_LIST);

        assertTrue(DLL.containsAll(OTHER_LIST));
    }

    @Test
    void containsAllFail() {
        assertThrows(NullPointerException.class, () -> DLL.containsAll(null));
    }

    @Test
    void removeAll() {
        DLL.add("a");
        DLL.add("b");
        DLL.add("c");

        OTHER_LIST = Arrays.asList("x", "y", "z");
        DLL.addAll(OTHER_LIST);

        assertTrue(DLL.removeAll(OTHER_LIST));
        assertEquals("a", DLL.get(0));
        assertEquals("b", DLL.get(1));
        assertEquals("c", DLL.get(2));
        assertEquals(3, DLL.size());
    }

    @Test
    void removeAllFail() {
        assertThrows(NullPointerException.class, () -> DLL.removeAll(null));
    }

    @Test
    void addAllIndex() {
        DLL.add("a");
        DLL.add("b");
        DLL.add("c");

        OTHER_LIST = Arrays.asList("x", "y", "z");

        assertTrue(DLL.addAll(1, OTHER_LIST));
        assertEquals(6, DLL.size());
    }

    @Test
    void addAllIndexFail() {
        DLL.add("a");
        DLL.add("b");
        DLL.add("c");

        assertThrows(NullPointerException.class, () -> DLL.addAll(1, null));
        assertThrows(IndexOutOfBoundsException.class, () -> DLL.addAll(10, OTHER_LIST));
        assertThrows(IndexOutOfBoundsException.class, () -> DLL.addAll(-1, OTHER_LIST));
    }

    @Test
    void retainAll() {
        DLL.add("a");
        DLL.add("b");
        DLL.add("c");

        OTHER_LIST = Arrays.asList("x", "y", "z");
        DLL.addAll(1, OTHER_LIST);

        assertTrue(DLL.retainAll(OTHER_LIST));
        assertEquals(3, DLL.size());
    }

    @Test
    void retainAllFail() {
        assertThrows(NullPointerException.class, () -> DLL.retainAll(null));
    }

    @Test
    void iterator() {
        DLL.add("x");
        DLL.add("y");
        DLL.add("z");

        OTHER_LIST = Arrays.asList("x", "y", "z");

        int i = 0;
        for (String s : DLL) {
            assertEquals(OTHER_LIST.get(i++), s);
        }
    }

    @Test
    void iteratorFail() {
        DLL.add("x");
        DLL.add("y");
        DLL.add("z");

        Iterator<String> it = DLL.iterator();
        it.next();
        it.next();
        it.next();
        assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    void clear() {
        DLL.clear();
        assertTrue(DLL.isEmpty());
        assertEquals(0, DLL.size());
    }
}