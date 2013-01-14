package sample.shared.result;

import java.util.Vector;

import net.customware.gwt.dispatch.shared.Result;

@SuppressWarnings("serial")
public class VectorDTO<R extends Result> extends Vector<R> implements Result
{
}
