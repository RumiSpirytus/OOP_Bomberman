package uet.oop.bomberman.graphics;

import javafx.scene.image.*;

/**
 * Lưu trữ thông tin các pixel của 1 sprite (hình ảnh game)
 */
public class sprite {
	
	public static final int DEFAULT_SIZE = 16;
	public static final int SCALED_SIZE = DEFAULT_SIZE * 2;
    private static final int TRANSPARENT_COLOR = 0xffff00ff;
	public final int SIZE;
	private int _x, _y;
	public int[] _pixels;
	protected int _realWidth;
	protected int _realHeight;
	private sprite_sheet _sheet;

	/*
	|--------------------------------------------------------------------------
	| Board sprites
	|--------------------------------------------------------------------------
	 */
	public static sprite grass = new sprite(DEFAULT_SIZE, 6, 0, sprite_sheet.tiles, 16, 16);
	public static sprite grass1 = new sprite(DEFAULT_SIZE, 0, 0, sprite_sheet.grass1, 16, 16);
	public static sprite brick = new sprite(DEFAULT_SIZE, 7, 0, sprite_sheet.tiles, 16, 16);
	public static sprite brick1 = new sprite(DEFAULT_SIZE, 0, 0, sprite_sheet.brick1, 16, 16);
	public static sprite wall = new sprite(DEFAULT_SIZE, 5, 0, sprite_sheet.tiles, 16, 16);
	public static sprite portal = new sprite(DEFAULT_SIZE, 4, 0, sprite_sheet.tiles, 16, 16);
	
	/*
	|--------------------------------------------------------------------------
	| Bomber Sprites
	|--------------------------------------------------------------------------
	 */
	public static sprite player_up = new sprite(DEFAULT_SIZE, 0, 0, sprite_sheet.tiles, 12, 16);
	public static sprite player_down = new sprite(DEFAULT_SIZE, 2, 0, sprite_sheet.tiles, 12, 15);
	public static sprite player_left = new sprite(DEFAULT_SIZE, 3, 0, sprite_sheet.tiles, 10, 15);
	public static sprite player_right = new sprite(DEFAULT_SIZE, 1, 0, sprite_sheet.tiles, 10, 16);
	
	public static sprite player_up_1 = new sprite(DEFAULT_SIZE, 0, 1, sprite_sheet.tiles, 12, 16);
	public static sprite player_up_2 = new sprite(DEFAULT_SIZE, 0, 2, sprite_sheet.tiles, 12, 15);
	
	public static sprite player_down_1 = new sprite(DEFAULT_SIZE, 2, 1, sprite_sheet.tiles, 12, 15);
	public static sprite player_down_2 = new sprite(DEFAULT_SIZE, 2, 2, sprite_sheet.tiles, 12, 16);
	
	public static sprite player_left_1 = new sprite(DEFAULT_SIZE, 3, 1, sprite_sheet.tiles, 11, 16);
	public static sprite player_left_2 = new sprite(DEFAULT_SIZE, 3, 2, sprite_sheet.tiles, 12 ,16);
	
	public static sprite player_right_1 = new sprite(DEFAULT_SIZE, 1, 1, sprite_sheet.tiles, 11, 16);
	public static sprite player_right_2 = new sprite(DEFAULT_SIZE, 1, 2, sprite_sheet.tiles, 12, 16);
	
	public static sprite player_dead1 = new sprite(DEFAULT_SIZE, 4, 2, sprite_sheet.tiles, 14, 16);
	public static sprite player_dead2 = new sprite(DEFAULT_SIZE, 5, 2, sprite_sheet.tiles, 13, 15);
	public static sprite player_dead3 = new sprite(DEFAULT_SIZE, 6, 2, sprite_sheet.tiles, 16, 16);
	
	/*
	|--------------------------------------------------------------------------
	| Character
	|--------------------------------------------------------------------------
	 */
	//BALLOM
	public static sprite balloom_left1 = new sprite(DEFAULT_SIZE, 9, 0, sprite_sheet.tiles, 16, 16);
	public static sprite balloom_left2 = new sprite(DEFAULT_SIZE, 9, 1, sprite_sheet.tiles, 16, 16);
	public static sprite balloom_left3 = new sprite(DEFAULT_SIZE, 9, 2, sprite_sheet.tiles, 16, 16);
	
	public static sprite balloom_right1 = new sprite(DEFAULT_SIZE, 10, 0, sprite_sheet.tiles, 16, 16);
	public static sprite balloom_right2 = new sprite(DEFAULT_SIZE, 10, 1, sprite_sheet.tiles, 16, 16);
	public static sprite balloom_right3 = new sprite(DEFAULT_SIZE, 10, 2, sprite_sheet.tiles, 16, 16);
	
	public static sprite balloom_dead = new sprite(DEFAULT_SIZE, 9, 3, sprite_sheet.tiles, 16, 16);
	
	//ONEAL
	public static sprite oneal_left1 = new sprite(DEFAULT_SIZE, 11, 0, sprite_sheet.tiles, 16, 16);
	public static sprite oneal_left2 = new sprite(DEFAULT_SIZE, 11, 1, sprite_sheet.tiles, 16, 16);
	public static sprite oneal_left3 = new sprite(DEFAULT_SIZE, 11, 2, sprite_sheet.tiles, 16, 16);
	
	public static sprite oneal_right1 = new sprite(DEFAULT_SIZE, 12, 0, sprite_sheet.tiles, 16, 16);
	public static sprite oneal_right2 = new sprite(DEFAULT_SIZE, 12, 1, sprite_sheet.tiles, 16, 16);
	public static sprite oneal_right3 = new sprite(DEFAULT_SIZE, 12, 2, sprite_sheet.tiles, 16, 16);
	
	public static sprite oneal_dead = new sprite(DEFAULT_SIZE, 11, 3, sprite_sheet.tiles, 16, 16);
	
	//Doll
	public static sprite doll_left1 = new sprite(DEFAULT_SIZE, 13, 0, sprite_sheet.tiles, 16, 16);
	public static sprite doll_left2 = new sprite(DEFAULT_SIZE, 13, 1, sprite_sheet.tiles, 16, 16);
	public static sprite doll_left3 = new sprite(DEFAULT_SIZE, 13, 2, sprite_sheet.tiles, 16, 16);
	
	public static sprite doll_right1 = new sprite(DEFAULT_SIZE, 14, 0, sprite_sheet.tiles, 16, 16);
	public static sprite doll_right2 = new sprite(DEFAULT_SIZE, 14, 1, sprite_sheet.tiles, 16, 16);
	public static sprite doll_right3 = new sprite(DEFAULT_SIZE, 14, 2, sprite_sheet.tiles, 16, 16);
	
	public static sprite doll_dead = new sprite(DEFAULT_SIZE, 13, 3, sprite_sheet.tiles, 16, 16);
	
	//Minvo
	public static sprite minvo_left1 = new sprite(DEFAULT_SIZE, 8, 5, sprite_sheet.tiles, 16, 16);
	public static sprite minvo_left2 = new sprite(DEFAULT_SIZE, 8, 6, sprite_sheet.tiles, 16, 16);
	public static sprite minvo_left3 = new sprite(DEFAULT_SIZE, 8, 7, sprite_sheet.tiles, 16, 16);
	
	public static sprite minvo_right1 = new sprite(DEFAULT_SIZE, 9, 5, sprite_sheet.tiles, 16, 16);
	public static sprite minvo_right2 = new sprite(DEFAULT_SIZE, 9, 6, sprite_sheet.tiles, 16, 16);
	public static sprite minvo_right3 = new sprite(DEFAULT_SIZE, 9, 7, sprite_sheet.tiles, 16, 16);
	
	public static sprite minvo_dead = new sprite(DEFAULT_SIZE, 8, 8, sprite_sheet.tiles, 16, 16);
	
	//Kondoria
	public static sprite kondoria_left1 = new sprite(DEFAULT_SIZE, 10, 5, sprite_sheet.tiles, 16, 16);
	public static sprite kondoria_left2 = new sprite(DEFAULT_SIZE, 10, 6, sprite_sheet.tiles, 16, 16);
	public static sprite kondoria_left3 = new sprite(DEFAULT_SIZE, 10, 7, sprite_sheet.tiles, 16, 16);
	
	public static sprite kondoria_right1 = new sprite(DEFAULT_SIZE, 11, 5, sprite_sheet.tiles, 16, 16);
	public static sprite kondoria_right2 = new sprite(DEFAULT_SIZE, 11, 6, sprite_sheet.tiles, 16, 16);
	public static sprite kondoria_right3 = new sprite(DEFAULT_SIZE, 11, 7, sprite_sheet.tiles, 16, 16);
	
	public static sprite kondoria_dead = new sprite(DEFAULT_SIZE, 10, 8, sprite_sheet.tiles, 16, 16);
	
	//ALL
	public static sprite mob_dead1 = new sprite(DEFAULT_SIZE, 15, 0, sprite_sheet.tiles, 16, 16);
	public static sprite mob_dead2 = new sprite(DEFAULT_SIZE, 15, 1, sprite_sheet.tiles, 16, 16);
	public static sprite mob_dead3 = new sprite(DEFAULT_SIZE, 15, 2, sprite_sheet.tiles, 16, 16);
	
	/*
	|--------------------------------------------------------------------------
	| Bomb Sprites
	|--------------------------------------------------------------------------
	 */
	public static sprite bomb = new sprite(DEFAULT_SIZE, 0, 3, sprite_sheet.tiles, 15, 15);
	public static sprite bomb_1 = new sprite(DEFAULT_SIZE, 1, 3, sprite_sheet.tiles, 13, 15);
	public static sprite bomb_2 = new sprite(DEFAULT_SIZE, 2, 3, sprite_sheet.tiles, 12, 14);
	
	/*
	|--------------------------------------------------------------------------
	| FlameSegment Sprites
	|--------------------------------------------------------------------------
	 */
	public static sprite bomb_exploded = new sprite(DEFAULT_SIZE, 0, 4, sprite_sheet.tiles, 16, 16);
	public static sprite bomb_exploded1 = new sprite(DEFAULT_SIZE, 0, 5, sprite_sheet.tiles, 16, 16);
	public static sprite bomb_exploded2 = new sprite(DEFAULT_SIZE, 0, 6, sprite_sheet.tiles, 16, 16);
	
	public static sprite explosion_vertical = new sprite(DEFAULT_SIZE, 1, 5, sprite_sheet.tiles, 16, 16);
	public static sprite explosion_vertical1 = new sprite(DEFAULT_SIZE, 2, 5, sprite_sheet.tiles, 16, 16);
	public static sprite explosion_vertical2 = new sprite(DEFAULT_SIZE, 3, 5, sprite_sheet.tiles, 16, 16);
	
	public static sprite explosion_horizontal = new sprite(DEFAULT_SIZE, 1, 7, sprite_sheet.tiles, 16, 16);
	public static sprite explosion_horizontal1 = new sprite(DEFAULT_SIZE, 1, 8, sprite_sheet.tiles, 16, 16);
	public static sprite explosion_horizontal2 = new sprite(DEFAULT_SIZE, 1, 9, sprite_sheet.tiles, 16, 16);
	
	public static sprite explosion_horizontal_left_last = new sprite(DEFAULT_SIZE, 0, 7, sprite_sheet.tiles, 16, 16);
	public static sprite explosion_horizontal_left_last1 = new sprite(DEFAULT_SIZE, 0, 8, sprite_sheet.tiles, 16, 16);
	public static sprite explosion_horizontal_left_last2 = new sprite(DEFAULT_SIZE, 0, 9, sprite_sheet.tiles, 16, 16);
	
	public static sprite explosion_horizontal_right_last = new sprite(DEFAULT_SIZE, 2, 7, sprite_sheet.tiles, 16, 16);
	public static sprite explosion_horizontal_right_last1 = new sprite(DEFAULT_SIZE, 2, 8, sprite_sheet.tiles, 16, 16);
	public static sprite explosion_horizontal_right_last2 = new sprite(DEFAULT_SIZE, 2, 9, sprite_sheet.tiles, 16, 16);
	
	public static sprite explosion_vertical_top_last = new sprite(DEFAULT_SIZE, 1, 4, sprite_sheet.tiles, 16, 16);
	public static sprite explosion_vertical_top_last1 = new sprite(DEFAULT_SIZE, 2, 4, sprite_sheet.tiles, 16, 16);
	public static sprite explosion_vertical_top_last2 = new sprite(DEFAULT_SIZE, 3, 4, sprite_sheet.tiles, 16, 16);
	
	public static sprite explosion_vertical_down_last = new sprite(DEFAULT_SIZE, 1, 6, sprite_sheet.tiles, 16, 16);
	public static sprite explosion_vertical_down_last1 = new sprite(DEFAULT_SIZE, 2, 6, sprite_sheet.tiles, 16, 16);
	public static sprite explosion_vertical_down_last2 = new sprite(DEFAULT_SIZE, 3, 6, sprite_sheet.tiles, 16, 16);
	
	/*
	|--------------------------------------------------------------------------
	| Brick FlameSegment
	|--------------------------------------------------------------------------
	 */
	public static sprite brick_exploded = new sprite(DEFAULT_SIZE, 7, 1, sprite_sheet.tiles, 16, 16);
	public static sprite brick_exploded1 = new sprite(DEFAULT_SIZE, 7, 2, sprite_sheet.tiles, 16, 16);
	public static sprite brick_exploded2 = new sprite(DEFAULT_SIZE, 7, 3, sprite_sheet.tiles, 16, 16);
	public static sprite ghost_left1 = new sprite(DEFAULT_SIZE, 6, 5, sprite_sheet.tiles, 16, 16);
	public static sprite ghost_left2 = new sprite(DEFAULT_SIZE, 6, 6, sprite_sheet.tiles, 16, 16);
	public static sprite ghost_left3 = new sprite(DEFAULT_SIZE, 6, 7, sprite_sheet.tiles, 16, 16);

	public static sprite ghost_right1 = new sprite(DEFAULT_SIZE, 7, 5, sprite_sheet.tiles, 16, 16);
	public static sprite ghost_right2 = new sprite(DEFAULT_SIZE, 7, 6, sprite_sheet.tiles, 16, 16);
	public static sprite ghost_right3 = new sprite(DEFAULT_SIZE, 7, 7, sprite_sheet.tiles, 16, 16);

	public static sprite ghost_dead = new sprite(DEFAULT_SIZE, 7, 8, sprite_sheet.tiles, 16, 16);
	/*
	|--------------------------------------------------------------------------
	| Powerups
	|--------------------------------------------------------------------------
	 */
	public static sprite powerup_bombs = new sprite(DEFAULT_SIZE, 0, 10, sprite_sheet.tiles, 16, 16);
	public static sprite powerup_flames = new sprite(DEFAULT_SIZE, 1, 10, sprite_sheet.tiles, 16, 16);
	public static sprite powerup_speed = new sprite(DEFAULT_SIZE, 2, 10, sprite_sheet.tiles, 16, 16);
	public static sprite powerup_wallpass = new sprite(DEFAULT_SIZE, 3, 10, sprite_sheet.tiles, 16, 16);
	public static sprite powerup_detonator = new sprite(DEFAULT_SIZE, 4, 10, sprite_sheet.tiles, 16, 16);
	public static sprite powerup_bombpass = new sprite(DEFAULT_SIZE, 5, 10, sprite_sheet.tiles, 16, 16);
	public static sprite powerup_flamepass = new sprite(DEFAULT_SIZE, 6, 10, sprite_sheet.tiles, 16, 16);
	
	public sprite(int size, int x, int y, sprite_sheet sheet, int rw, int rh) {
		SIZE = size;
		_pixels = new int[SIZE * SIZE];
		_x = x * SIZE;
		_y = y * SIZE;
		_sheet = sheet;
		_realWidth = rw;
		_realHeight = rh;
		load();
	}
	
	public sprite(int size, int color) {
		SIZE = size;
		_pixels = new int[SIZE * SIZE];
		setColor(color);
	}
	
	private void setColor(int color) {
		for (int i = 0; i < _pixels.length; i++) {
			_pixels[i] = color;
		}
	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				_pixels[x + y * SIZE] = _sheet._pixels[(x + _x) + (y + _y) * _sheet.SIZE];
			}
		}
	}
	
	public static sprite movingSprite(sprite normal, sprite x1, sprite x2, int animate, int time) {
		int calc = animate % time; //0
		int diff = time / 3;  //6
		
		if(calc < diff) {
			return normal;
		}
			
		if(calc < diff * 2) {
			return x1;
		}
			
		return x2;
	}
	
	public static sprite movingSprite(sprite x1, sprite x2, int animate, int time) {
		int diff = time / 2;
		return (animate % time > diff) ? x1 : x2; 
	}
	
	public int getSize() {
		return SIZE;
	}

	public int getPixel(int i) {
		return _pixels[i];
	}

	public Image getFxImage() {
        WritableImage wr = new WritableImage(SIZE, SIZE);
        PixelWriter pw = wr.getPixelWriter();
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if ( _pixels[x + y * SIZE] == TRANSPARENT_COLOR) {
                    pw.setArgb(x, y, 0);
                }
                else {
                    pw.setArgb(x, y, _pixels[x + y * SIZE]);
                }
            }
        }
        Image input = new ImageView(wr).getImage();
        return resample(input, SCALED_SIZE / DEFAULT_SIZE);
    }

	private Image resample(Image input, int scaleFactor) {
		final int W = (int) input.getWidth();
		final int H = (int) input.getHeight();
		final int S = scaleFactor;

		WritableImage output = new WritableImage(
				W * S,
				H * S
		);

		PixelReader reader = input.getPixelReader();
		PixelWriter writer = output.getPixelWriter();

		for (int y = 0; y < H; y++) {
			for (int x = 0; x < W; x++) {
				final int argb = reader.getArgb(x, y);
				for (int dy = 0; dy < S; dy++) {
					for (int dx = 0; dx < S; dx++) {
						writer.setArgb(x * S + dx, y * S + dy, argb);
					}
				}
			}
		}

		return output;
	}
}
