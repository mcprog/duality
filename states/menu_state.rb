require 'singleton'
require 'gosu'
require_relative '../library/position'

class MenuState < GameState
  include Singleton

  def initialize
    @menu_title = Gosu::Image.from_text("Menu", 96)
    @play_ssp = Gosu::Image.from_text("SSP", 84)
  end

  def update(dt)

  end

  def draw
    @menu_title.draw(Position.get_x_centered_from_width(@menu_title.width),0,0)
    @play_ssp.draw(
        Position.get_x_centered_from_width(@play_ssp.width),
        Position.get_y_centered_from_height(@play_ssp.height), 0)
  end

end
