package pl.gramto.e;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Align;

public class Addons {

	private static boolean touchUp, beenTouch;

	private static Vector mousePosition = new Vector(0, 0);

	public static void setMousePosition(int x, int y) {
		mousePosition = new Vector(x, y);
	}

	public static Vector getMousePosition() {
		return mousePosition;
	}

	public enum STATUS {
		MENU, QUALIFICATION, TEST, RESULT, INFO, EXIT
	}

	public static final String POLISH_CHARS = "aąbcćdeęfghijklłmnńoópqrsśtuvwxyzźżAĄBCĆDEĘFGHIJKLŁMNŃOÓPQRSŚTUVWXYZŹŻ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>Ω";

	public static void updateTouchUp() {
		touchUp = false;
		if (Gdx.input.isTouched())
			beenTouch = true;
		else if (!Gdx.input.isTouched() && beenTouch) {
			touchUp = true;
			beenTouch = false;
		}
		
	}

	public static boolean justTouchUp() {
		return touchUp;
	}
	
	public static String br(String text, GlyphLayout layout, BitmapFont font, float maxSize){
		if(text.length()>0){
			layout.setText(font, String.valueOf("A"),Color.WHITE,Gdx.graphics.getWidth(),Align.center,true);
			float posInLine = layout.width;
			for (int a = 0; a < text.length(); a++) {
				if((text.charAt(a) != ' ')){
					layout.setText(font, String.valueOf(text.charAt(a)),Color.WHITE,Gdx.graphics.getWidth(),Align.center,true);
					posInLine += layout.width;
				}
				else {
					layout.setText(font, "A",Color.WHITE,Gdx.graphics.getWidth(),Align.center,true);
					posInLine += layout.width;
				}
				if (posInLine >= maxSize) {
					boolean isSpace = false;
					for (int b = a; b > 0; b--) {
						if (text.charAt(b) == ' ') {
							text = text.substring(0, b) + "\n"
									+ text.substring(b + 1, text.length());
							a = b - 2;
							isSpace = true;
							break;
						}
					}
					if (!isSpace) {
						if(a>3)
							text = text.substring(0, a-4) + "\n" + text.substring(a-4, text.length());
					}
					layout.setText(font, String.valueOf("A"),Color.WHITE,Gdx.graphics.getWidth(),Align.center,true);
					posInLine = layout.width;
				}
				if((text.charAt(a) == '\n')){
					layout.setText(font, String.valueOf("A"),Color.WHITE,Gdx.graphics.getWidth(),Align.center,true);
					posInLine = layout.width;
				}
			}
		}
		return text;
	}
	
	public static String parse(String str){
		
		String[] a = new String[10];
		a[0] = "[br]";
		a[1] = "[i]";
		a[2] = "[/i]";
		a[3] = "[b]";
		a[4] = "[/b]";
		a[5] = "[_]";
		a[6] = "[/_]";
		a[7] = "[']";
		a[8] = "[sup]";
		a[9] = "[/sup]";
		String[] b = new String[10];
		b[0] = "\n";
		b[1] = "";
		b[2] = "";
		b[3] = "";
		b[4] = "";
		b[5] = "";
		b[6] = "";
		b[7] = "\"";
		b[8] = "";
		b[9] = "";
		
		for(int i = 0; i < a.length; i++)
			str = str.replace(a[i], b[i]);
		return str;
	}
	
	public static String getFileName(String addr){
		int numSleshes = 0;
		for(int a=addr.length()-1; a>0; a--){
			if(addr.charAt(a) == '/')
				numSleshes++;
				
			if(numSleshes==2)
				return addr.substring(a + 1, addr.length());
		}
		return addr;
	}
}
