using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;

namespace Duality
{
    public interface ICanBeDrawn
    {
        void Draw(SpriteBatch spriteBatch, Vector2 location);
    }
}