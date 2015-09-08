require_relative 'drawable'

class Splash

  def initialize(duration = 1000)

    @splash_timer = duration
    @@splash_text_img = Drawable.new("assets/textures/beer.png", width: 200)
  end

  def update(dt)
    @splash_timer -= dt
  end

  def draw
    if @splash_timer > 0
      @@splash_text_img.draw_from_center
    end
  end

end
