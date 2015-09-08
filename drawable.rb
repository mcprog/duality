require 'gosu'

class Drawable

  # Constructor
  # requires: string path to image file (including file extension)
  # z: is the z-order to define the drawing order
  # if width and height aren't defined, actual image height will be used
  def initialize(path, x: 0, y: 0, z: 0, width: nil, height: nil)
    @x, @y, @z = x, y, z
    @image = Gosu::Image.new(path)

    # aspect ratio relative to height (.to_f to avoid truncation loss)
    width_over_height = @image.width.to_f / @image.height.to_f

    # logics that determins the @width field.
    # Rules:
    # 1. Width and height must be defined to set to width
    # 2. If neither height nor width is defined then set to image width
    # 3. If width is not defined, but height is, set to portion of height (by use of aspect ratio)
    @width = (width.nil?) ? (height.nil?) ? @image.width : height * width_over_height : width
    # logic that determines the @ height field
    # same as above except divide aspect ratio
    @height = (height.nil?) ? (width.nil?) ? @image.height : width / width_over_height : height

    @scl_x = @width / @image.width.to_f
    @scl_y = @height / @image.height.to_f
  end

  # draws image at the center of the screen from the center of the image
  def draw_center_screen
    @image.draw((WIDTH - @image.width * @scl_x) / 2 + @x, (HEIGHT - @image.height * @scl_y) / 2 + @y, @z, @scl_x, @scl_y)
  end

  # draws image from an origin point on the center of the image with the displacement given from the x and y args
  def draw_from_center(x = @x, y = @y)
    @image.draw(-@image.width * @scl_x / 2 + x, -@image.height * @scl_y / 2 + y, @z, @scl_x, @scl_y)
  end
end
