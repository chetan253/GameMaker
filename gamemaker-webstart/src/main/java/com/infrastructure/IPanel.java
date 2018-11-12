package com.infrastructure;

public interface IPanel {
	public void addComponent(IComposite composite) throws Exception;
	public void removeComponent(IComposite composite) throws Exception;
	public void addComponent(AbstractComponent asbtractComponent) throws Exception;
	public void removeComponent(AbstractComponent asbtractComponent) throws Exception;
}
