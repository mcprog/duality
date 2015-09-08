require 'gosu'
require_relative 'states/game_state'
require_relative 'states/splash_state'
require_relative 'game_window'

module DualityGame

  def self.assets_path(file_name)
    File.join(File.dirname(File.dirname(__FILE__)), 'assets', file_name)
  end

end

$window = GameWindow.new
GameState.switch(SplashState.instance)
$window.show
