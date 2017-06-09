package yyz.njupt.view;

import java.awt.Point;
import java.awt.Toolkit;

public class WindowsXY {
	
	public static Point getXY(java.awt.Dimension d){
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int height = toolkit.getScreenSize().height;
		int width = toolkit.getScreenSize().width;
		
		return new Point((width - d.width) / 2, (height - d.height) / 2 - 200);
	}

}
