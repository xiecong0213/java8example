# java8 tutorial

## FuntionalInterface
  
  An interface is a functional interface if it defines exactly one abstract method.
For instance, java.lang.Runnable is a functional interface because it only defines one abstract
simple example of functional interface for add two numbers Desipte explict @FunctionalInterface is unnecessary,code it when you create a functional interface can help you find error in compile time.Just like @override,if you don't define exactly one abstrct method,compiler will hint you with error mark.
see com.tutorial.java8.lambda.FunctionalInteraceExample for more detal examples.

```
@FunctionalInterface
public interface Consumer<T> {

    /**
     * Performs this operation on the given argument.
     *
     * @param t the input argument
     */
    void accept(T t);

    default Consumer<T> andThen(Consumer<? super T> after) {
        Objects.requireNonNull(after);
        return (T t) -> { accept(t); after.accept(t); };
    }
}

```

## Lambda Expression

Lambda expressions are introduced in Java 8 and are touted to be the biggest feature of Java 8. Lambda expression facilitates functional programming, and simplifies the development a lot.

1. common usage of lambda exspression:

	```
        //full lambda expression: comma-separated list of inputs params with specific types, a block with a return on the right
        FunctionalInteraceExample<Integer> fullExpression = (Integer l, Integer r) -> {
            return l + r;
        };
        System.out.println(fullExpression.add(1, 2));// expect output=3

        //abbrev lambda expression(more than one params): omit param type,method block,return
        FunctionalInteraceExample<Integer> abbrevExpreesionWithMultiParams = (l, r) -> l + r;
        System.out.println(abbrevExpreesionWithMultiParams.add(1, 2));//expect output=3


        //abbrev lambda expression(only one param): omit param type,method block,return, addtional omit param brackets
        IncrNumber<Integer> abbrevExpreesionWithOneParam = x -> x + 1;
        System.out.println(abbrevExpreesionWithOneParam.incr(1));//expect output=2

        //abbrev lambda expression(no param): omit param type,param brackets,method block,return
        PrintHello abbrevExpreesionWithNoParam = () -> System.out.println("hello lambda");
        abbrevExpreesionWithNoParam.print();//expect output=hello lambda
```

2. Method Reference

	```
	Method reference is abbreviation of lambda expression

  	Method reference	 	Equivalent lambda expression
  	-------------------------------------------------
  	String::valueOf			x -> String.valueOf(x)
  	Object::toString		(x) -> x.toString()
  	x::toString		    	() -> x.toString()
  	ArrayList::new			() -> new ArrayList<>()
  	
  	some code example:
  	
  	StaticMethodReference staticMethodReference = Long::valueOf;//equal: (String x) -> Long.valueOf(x)
        System.out.println("staticMethodReference: " + staticMethodReference.convertStr2Long("123"));

        ObjectMethodReference objectMethodReference = Object::toString;//equal: (x) -> x.toString()
        System.out.println("objectMethodReference: " + objectMethodReference.convertLong2Str(321L));

        AtomicLong l = new AtomicLong(0);
        NonStaticMethodReference nonStaticMethodReference = l::incrementAndGet; //equal: () -> l.incrementAndGet()
        System.out.println("nonStaticMethodReference: " + nonStaticMethodReference.incr());

        ConstructorMethodReference<Integer> constructorMethodReference = ArrayList<Integer>::new; // equal: () -> new ArrayList<>();
        ArrayList<Integer> arrayList = constructorMethodReference.get();
        System.out.println("constructorMethodReference: " + arrayList);
	```
	
3. jdk8 support common functional inferface for lambda

   ```
  a lot of commonly useful FunctionalInterface are added to JDK8.
      Function<T,R> T as input,R as output,use for map one object to another object 
      Predicate<T> T as input,return Boolean,use for filter and other scenario which need judge true or false
      Consumer<T> - T as input, perform some action and don't return anything
      Supplier<T> - with nothing as input, return a T
      BinaryOperator<T> - take two T's as input, return one T as output, useful for "reduce" operations
      ......
   
   some examples:
   
   private static void functionExample(List<Integer> list, Function<Integer,Long> sqrFunction){
        list.stream().map(sqrFunction).forEach(x -> System.out.print(x + " "));
    }

    private static void predicateExample(List<Integer> list, Predicate<Integer> predicate){
        list.stream().filter(predicate).forEach(x -> System.out.print(x + " "));
    }

    private static void consumerExample(List<Integer> list, Consumer<Integer> integerConsumer){
        list.stream().forEach(integerConsumer);
    }

    private static ArrayList<Integer> supplierExample(Supplier<ArrayList<Integer>> supplier)
    {
        return supplier.get();
    }

    private static Optional<Integer> binaryOperatorExample(List<Integer> list, BinaryOperator<Integer> binaryOperator){
        return list.stream().reduce(binaryOperator);
    }
   ```
      
4. Capturing

	```
	Lambdas are said to be "capturing" if they access a non-static variable or object that was defined outside of the lambda body. For example, this lambda captures the variable x:
int x = 5;
return y -> x + y;
this may cause performance problem,when execute the capturing lambda expression,a new instance of an anonymous class will be created.
output of the object hashcode proved capturing may cause performance problem
@see com.tutorial.java8.lambda.LambdaExpressionCapturing
	```
5. Constrain

	
	<ul>
          <li>can't change non-final variable in lambda expression</li>
          <li>can't throw checked exception in lambda expression</li>
          <li>can't break,early return</li>
    </ul>
    
	```
	final String secret = "foo";
    boolean canNotEarlyReturn(Iterable<String> values) {
        values.forEach(s -> {
            if (secret.equals(s)) {
                 // want to end the loop and return true, but can't
            }
        });
        return true;
    }

    //can't throw checked exception,besides you can convert unchecked exception to RuntimeException
    void canNotThrowUncheckException(Iterable<String> values, Appendable out)
            throws IOException { // doesn't help with the error
        values.forEach(s -> {
//            out.append(s); // error: can't throw IOException here
            // Consumer.accept(T) doesn't allow it
        });
    }

    void canNotChangeNonFinalVar(){
        int x = 1;
        //comment line can't pass compile
//        IntStream.range(0,10).forEach(i -> x++); //error: despite x is non-final
    }
	```
	
6. Finally，factorypattern package show some lambda expression in design pattern

## Stream

A stream is something like an iterator. The values "flow past" (analogy to a stream of water) and then they're gone. A stream can only be traversed once, then it's used up. Streams may also be infinite.

Streams can be sequential or parallel. They start off as one and may be switched to the other using stream.sequential() or stream.parallel(). The actions of a sequential stream occur in serial fashion on one thread. The actions of a parallel stream may be happening all at once on multiple threads.

A stream provides a fluent API for transforming values and performing some action on the results. Stream operations are either "intermediate" or "terminal".

1. Create Stream

	``` 
 	* call collection.stream() or parallelStream method get a stream
 	* Arrays.stream(Object[])
 	* Stream static method, Stream.of, IntStream.range,Stream.generate,Stream.iterate</li>
 	* get line stream from file use. BufferedReader.lines()
 	* Files operate method,list,find,walk
 	* Random.ints()
 	* other static method,BitSet.stream(),Pattern.splitAsStream(java.lang.CharSequence)
 	
 	examples:
 	@see com.tutorial.java8.stream.StreamCreate
	```
2. Intermediate operate

	```	
	 Intermediate - An intermediate operation keeps the stream open and allows further operations to follow.The return type of these methods is Stream; they return the current stream to allow chaining of more operations.
	 * filter:Returns a stream consisting of the elements of this stream that match the given predicate
	 * map:Returns a stream consisting of the results of applying the given function to the elements of this stream
	* flatMap:Returns a stream consisting of the results of replacing each element of this stream with the contents of a mapped stream produced by applying the provided mapping function to each element.
	* peek:Perform some action on each element as it is encountered. Primarily useful for debugging
	* distinct: Exclude all duplicate elements according to their .equals behavior. This is a stateful operation.
	* sorted: Ensure that stream elements in subsequent operations are encountered according to the order imposed by a Comparator. This is a stateful operation.
	* limit:Ensure that subsequent operations only see up to a maximum number of elements. This is a stateful, short-circuiting operation.
	* skip:Ensure that subsequent operations do not see the first n elements. This is a stateful operation.
	
	examples:
	
	Map<String, List<Student>> school = new HashMap();
        school.put("mathClass", Arrays.asList(new Student("Joe", 21, 96.0), new Student("Lily", 23, 99.0),new Student("LiLei",24,59.0)));
        school.put("SportClass", Arrays.asList(new Student("James", 24, 98.0), new Student("Messi", 28, 100.0),new Student("HanMeiMei",24,56.0)));
        school.put("filterdClass", Arrays.asList(new Student("James", 24, 98.0), new Student("Messi", 28, 100.0),new Student("HanMeiMei",24,56.0)));

        //order by student score desc
        Map<String, List<Student>> ordered = school.entrySet().stream().filter(e -> e.getKey() != "filterdClass")
                .collect(Collectors
                        .toMap(Map.Entry::getKey, e -> e.getValue().stream().filter(v -> v.getStore() > 60).sorted((x, y) -> (int) (y.getStore() - x.getStore()))
                                .collect(Collectors.toList())));
        System.out.println(ordered);
	```
	
3. Terminal Operation

	```
	* forEach: Perform some action for each element in the stream.
	* toArray: Dump the elements in the stream to an array.
	* reduce: Combine the stream elements into one using a BinaryOperator.
	* collect: Dump the elements in the stream into some container, such as a Collection or Map.
	* min: Find the minimum element of the stream according to a Comparator.
	* max: Find the maximum element of the stream according to a Comparator.
	* count: Find the number of elements in the stream.
	* anyMatch: Find out whether at least one of the elements in the stream matches a Predicate. This is a short-circuiting operation.
	* allMatch: Find out whether every element in the stream matches a Predicate. This is a short-circuiting operation.
	* noneMatch: Find out whether zero elements in the stream match a Predicate. This is a short-circuiting operation.
	* findFirst: Find the first element in the stream. This is a short-circuiting operation.
	* findAny: Find any element in the stream, which may be cheaper than findFirst for some streams. This is a short-circuiting operation.
	
	some examples:
	
	System.out.println("collect:");
        List<Integer> collectionList = IntStream.range(0, 5).boxed().filter(x -> x % 2 == 0).collect(Collectors.toList());
        System.out.println(collectionList);

        Set<Integer> collectionSet = IntStream.range(0, 5).boxed().filter(x -> x % 2 == 0).collect(Collectors.toSet());
        System.out.println(collectionSet);

        Map<Integer, Integer> collectionMap = IntStream.range(0, 5).boxed().filter(x -> x % 2 == 0).collect(Collectors.toMap(x -> x, x -> 2 * x));
        System.out.println(collectionMap);

        Collector<Student, StringJoiner, String> personNameCollector =
                Collector.of(
                        () -> {
                            System.out.println("supplier");
                            return new StringJoiner(" | ");
                        },
                        (j, p) -> {
                            System.out.format("accumulator: p=%s; j=%s\n", p, j);
                            j.add(p.getName().toUpperCase() + "");
                        },
                        (j1, j2) -> {
                            System.out.println("merge");
                            return j1.merge(j2);
                        },
                        j -> {
                            System.out.println("finisher");
                            return j.toString();
                        });

        String names = Arrays.asList(new Student("Joe", 21, 96.0), new Student("Lily", 23, 99.0),new Student("LiLei",24,59.0))
                .parallelStream()
                .collect(personNameCollector);

        System.out.println(names);
	```
4. Parallel Stream

	```
	
	The actions of a parallel stream may be happening all at once on multiple threads.
	parallel stream can promote performance,particular in big stream
	you can use vm param control parallel threadpool
-Djava.util.concurrent.ForkJoinPool.common.parallelism=5

	examples:
	List<Student> studentList = Arrays.asList(new Student("Joe", 21, 96.0), new Student("Lily", 23, 99.0),new Student("LiLei",24,59.0),new Student("James",29,88.0));

        Integer ageSum = studentList.parallelStream().reduce(0,(sum,student)->{
            System.out.format("accumulator: sum=%s; person=%s; thread=%s\n",
                    sum, student, Thread.currentThread().getName());
            return sum+=student.getAge();
        },(sum1,sum2) -> {
            System.out.format("combiner: sum1=%s; sum2=%s; thread=%s\n",
                    sum1, sum2, Thread.currentThread().getName());
            return sum1 + sum2;
        });

	```
## TO BE CONTINUE
