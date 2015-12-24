package com.autumncode.automata.life2d;

/**
 * This represents a class that knows how to render a generation of a 2D Cellular Automaton. Each call to
 * <code>render()</code> should create a representation of that generation.
 */
public interface Renderer {
    /**
     * Generates a rendering of the generation. Note that the Renderer is responsible for actually writing the
     * output.
     *
     * This facilitates multiple output formats; there's nothing wrong with a Renderer being stateful, for example,
     * and writing subsequent generations in a given automaton to a graphics image's rows, for example.
     * @param generation the current generation to render
     */
    void render(Generation generation);
}
