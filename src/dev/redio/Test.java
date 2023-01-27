package dev.redio;

import dev.redio.typeinfo.Retain;

@Retain
public interface Test<@Retain T> {

    @Retain
    <@Retain F> void foo(F foo);
}
