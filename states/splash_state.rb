require 'singleton'
require_relative '../drawable'
require_relative 'menu_state'

class SplashState < GameState
  include Singleton
  attr_accessor :menu_state

  def initialize

    @splash_timer = 3000
    @@splash_text_img = Drawable.new(DualityGame.assets_path("textures/opensource.png"), height: HEIGHT * 0.75)
  end

  def update(dt)
    @splash_timer -= dt
    if @splash_timer <= 0
      GameState.switch(MenuState.instance)
    end
  end

  def draw
    @@splash_text_img.draw_center_screen
  end

end
