use coroutine::asymmetric::*;

let mut coro = Coroutine::spawn(|coro, val| {
    println!("Inside {}", val);
    coro.yield_with(val + 1)
});

println!("Resume1 {}", coro.resume(0).unwrap());
println!("Resume2 {}", coro.resume(2).unwrap());