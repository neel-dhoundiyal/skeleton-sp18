public class LinkedListDeque <T>{
    private Node sentinel;
    private int size;
    public class Node {
        public T first;
        public Node prev;
        public Node next;
        public Node(T f, Node y, Node p){
            first = f;
            next = y;
            prev = p;
        }
    }
    public LinkedListDeque(){
        sentinel = new Node(null,null,null);
        size = 0 ;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }
    public void addLast(T item){
        Node y = sentinel.prev;
        sentinel.prev = new Node(item,sentinel,y.prev);
        size += 1;
    }
    public void addFirst(T item){
        sentinel.next = new Node(item, sentinel.next, sentinel);
        size += 1;
    }
    public boolean isEmpty(){
        if(this.size == 0)
            return true;
        return false;
    }
    public int size(){
        return this.size;
    }
    public void printDeque(){
        Node p = sentinel.next;
        while(p.next != sentinel){
            System.out.print(p.first + " ");
            p = p.next;
        }
    }
    public T removeFirst(){
        if (this.size == 0)
                return null;
        Node p = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.next.prev = sentinel;
        size -=1;
        return p.first;
    }
    public T removeLast(){
        if (this.size == 0){
            return null;
        }
        Node p = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return p.first;
    }
    public T get(int index){
        int x = size;
        Node y = sentinel.prev;
        while(x != index) {
            x -= 1;
            y = y.prev;
        }
        return y.first;
    }
}