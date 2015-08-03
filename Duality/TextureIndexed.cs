using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;

namespace Duality
{
    public class TextureIndexed : ICanBeDrawn
    {
        public TextureAtlas Atlas;

        public TextureIndexed(Texture2D texture, int rows, int columns, int index)
        {
            Atlas.Texture = texture;
            Atlas.Rows = rows;
            Atlas.Columns = columns;
            Atlas.Index = index;

        }

        public TextureIndexed(TextureAtlas atlas)
        {
            Atlas = atlas;
        }

        public void Draw(SpriteBatch spriteBatch, Vector2 location)
        {
            int width = Atlas.Texture.Width / Atlas.Columns;
            int height = Atlas.Texture.Height / Atlas.Rows;
            int row = (int)((float)Atlas.Index / (float)Atlas.Columns);
            int column = Atlas.Index % Atlas.Columns;

            Rectangle sourceRectangle = new Rectangle(width * column, height * row, width, height);
            Rectangle destinationRectangle = new Rectangle((int)(location.X - width / 2f), (int)(location.Y - height / 2f), width, height);

            spriteBatch.Begin();
            spriteBatch.Draw(Atlas.Texture, destinationRectangle, sourceRectangle, Color.White);
            spriteBatch.End();
        }
    }
}