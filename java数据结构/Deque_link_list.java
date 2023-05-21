//双端队列
//单链表要删除元素是,需要知道上一个节点,
//双向链表删除元素可以不需要知道上一个节点是谁
//双向环型链表实现
// Java中的泛型可以用来编写更通用和更类型安全的代码，使得代码的复用和维护更加方便。下面是Java中泛型的基本用法：

// 定义泛型类：在类名后加上尖括号，里面放置类型变量，例如：public class MyClass<T> {}

// 定义泛型方法：在访问修饰符和返回值类型之间加上尖括号，里面放置类型变量，例如：public <T> void myMethod(T arg) {}

// 实例化泛型对象：在构造函数后面使用尖括号指定具体的类型，例如：MyClass<Integer> myObj = new MyClass<>();

// 泛型通配符：尖括号里使用?表示不确定的类型，例如：List<?> list = new ArrayList<>();

// 上限通配符：尖括号里使用extends关键字限制泛型类型的上界，例如：List<? extends Number> list = new ArrayList<>();

// 下限通配符：尖括号里使用super关键字限制泛型类型的下界，例如：List<? super Integer> list = new ArrayList<>();

// 在使用泛型时，需要注意以下几点：

// 类型变量可以是任意标识符，但一般用大写字母表示。

// 泛型只在编译期生效，在运行期会被擦除，这意味着泛型参数的具体类型信息在运行期不可用。因此，在泛型代码中不能使用 instanceof 操作符。

// 泛型类和泛型方法可以具有多个类型参数，使用逗号分隔。

// 泛型类或泛型方法中的类型变量可以在多个地方使用，例如：类中的字段、方法的参数和返回值类型等。

// 在泛型代码中，可以使用泛型接口和泛型继承，来扩展泛型的灵活性和功能。

// 以上是Java中泛型的基本使用方法和注意事项。泛型的使用能够提高代码的可读性和可维护性，也是Java面向对象编程的一个重要概念。
import java.util.Iterator;

public class Deque_link_list<E> implements Iterator<E> {
   // 范型代表任意类型,在类中使用时不可以使用明确类型
   static class node<E> {
      // 节点类
      E val;
      node<E> prev;
      node<E> next;

      node(node<E> prev, E val, node<E> next) {
         this.prev = prev;
         this.val = val;
         this.next = next;

      }

   }

   int capacity;
   int size;
   node<E> s = new node<>(null, null, null);// 哨兵节点

   Deque_link_list(int capacity) {
      this.capacity = capacity;
      s.next = s;
      s.prev = s;

   }

   public boolean push_first(E val) {
      if (isFull()) {
         return false;
      }
      node<E> care = new node<E>(s, val, s.next);
      node <E> a= s ;
      node <E> b= s.next  ;
      a.next =care ;
      b.prev=care ;
      return true;
     
     
      

   }

   public boolean push_last(E val) {
      if (size >= capacity) {
         return false;
      }
      node<E> care = new node<E>(s.prev, val, s);
      s.prev.next = care;
      s.prev = care;
      size++;
      return true;

   }

   public node<E> popfirst() {
      if (!isEmpety()) {
         node<E> remove = s.next;
         s.next = remove.next;
          remove.next.prev=s;
         size--;
         return remove;
      }
      return null;

   }

   public node<E> poplast() {

      if (!isEmpety()) {
         node<E> remove = s.prev;
         s = remove.prev.next;
         remove.prev = s.prev;
         size--;
         return remove;
      }
      return null;

   }

   public node<E> peekfirst() {
      if (!isEmpety()) {
         return s.next;
      }
      return null;

   }

   public node<E> peeklast() {
      if (!isEmpety()) {
         return s.prev;
      }
      return null;

   }

   public boolean isEmpety() {
      return size == 0;

   }

   public boolean isFull() {
      return size == capacity;
   }

   public void print() {
      node<E> care = s.next;
      while (care != s)

      {
         System.out.println(care.val);
         care =care .next;
      }

   }

   public Iterator<E> iterator() {
      return new Iterator<E>() {
         node<E> p = s.next;

         public boolean hasNext() {
            return p != s;
         }

         public E next() {
            E val = p.val;
            p = p.next;
            return val;
         }

      };
   }

   @Override
   public boolean hasNext() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'hasNext'");
   }

   @Override
   public E next() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'next'");
   }

}

class test10 {
   public static void main(String[] args) {
Deque_link_list <Integer > test =new Deque_link_list<>(10);
test .push_last (1);
test .push_last(2);
test .push_last(3);
test .push_last(4);

test .push_last(7);
test .push_last(8);
// test .popfirst();
System.out.println(test .   peekfirst ().val); 



   }

}