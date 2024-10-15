
public class LinkedListDeque <any>{

    
    private node node(Object object, Object object0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
      
    public class node{
            private  any item;
            private  node prev;
            private  node next;

        public node(any n,node p,node q){
            any item=n;
            node prev=p;
            node next=q;



        }
        public node (node p ,node q){
          node prev=p;
          node  next=q;
        }
        
    }
    private node sentinel;
    private  int size;
    public  LinkedListDeque(){
         sentinel=node(null,null);
         sentinel.prev=sentinel;
         sentinel.next=sentinel;
        size=0;


    }
    public boolean isEmpty(){
        return  size==0;
    }
    public  int size(){
        return size;
    }
    public void addFirst(any item)
    {
        node first=new node(item ,sentinel, sentinel.next);
        sentinel.next.prev=first ;//一定要先写这行考虑到直有一个节点的情况，在这一步前node还没插进来


        sentinel.next= first;

        size++;

    }
    public void addLast(any item){
        node last=new node( item ,sentinel.next,sentinel);
        sentinel.prev.next = last;
        sentinel.prev=last;



    }
    public any removeFirst(){

        if (sentinel.prev==sentinel){
            return null;
        }
        else{
            any val=sentinel.next.item;
            sentinel.next.next.prev=sentinel;
            sentinel.next=sentinel.next.next;

            size--;
            return val;
        }
    }
    public any removeLast(){
        if (size==0){
            return null;
        }
      any val=sentinel.next.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev=sentinel.prev.prev;
        return val;



    }


    public void printDeque(){
        node index=sentinel.next;
        while (index!=sentinel){
            System.out.print(index.item+" ");
            index=index.next;
        }
    }

    public any  get(int index){
     if (index>=size){
         return null;
     }
     node p=sentinel;
     for(int i=0;i<index;i++){
         p=p.next;
     }
     return p.item;
    }
    private any  getRecursiveHelp(node start, int index) {
        if (index == 0) {
            return start.item;
        } else {
            return getRecursiveHelp(start.next, index - 1);
        }
    }
    public  any  getRecursive(int index)
    {
        if (index>=size){
            return null;
        }
        return getRecursiveHelp(sentinel.next,index);
    }








   

    

}