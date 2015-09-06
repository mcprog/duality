require_relative 'drawable'

class Splash

  def initialize

    @splash_timer = 0

    #splash_text = "Duality: pre-Alpha"
    #@@splash_text_img = Gosu::Image.new("assets/textures/opensource.png")
    @@splash_text_img = Drawable.new("assets/textures/ohmyweb.png", height: 100)
  end

  def update(dt)
    @splash_timer += dt
  end

  def draw
    if @splash_timer <= 10000

      #@@splash_text_img.draw(WIDTH / 2 - @@splash_text_img.width / 2 * 0.5, HEIGHT / 2 - @@splash_text_img.height / 2 * 0.5, 0, 0.5, 0.5)
      @@splash_text_img.draw_from_center
    end
  end


end
