require 'gosu'

class Drawable

  def initialize(path, x: 0, y: 0, z: 0, width: nil, height: nil)
    @x, @y, @z = x, y, z
    @image = Gosu::Image.new(path)
    width_over_height = @image.width.to_f / @image.height.to_f
    @width = (width.nil?) ? (height.nil?) ? @image.width : height * width_over_height : width
    @height = (height.nil?) ? (width.nil?) ? @image.height : width / width_over_height : height
  end

  def draw_from_center(x = @x, y = @y)
    scl_x = @width / @image.width.to_f
    scl_y = @height / @image.height.to_f
    @image.draw((WIDTH - @image.width * scl_x) / 2 + x, (HEIGHT - @image.height * scl_y) / 2 + y, @z, scl_x, scl_y)
  end
end
