using Microsoft.Xna.Framework.Graphics;

namespace Duality
{
    public struct TextureAtlas
    {
        public Texture2D Texture { get; set; }
        public int Rows { get; set; }
        public int Columns { get; set; }
        public int Index { get; set; }

        public TextureAtlas(Texture2D texture, int rows, int columns, int index)
        {
            Texture = texture;
            Rows = rows;
            Columns = columns;
            Index = index;
        }

        public TextureAtlas(Texture2D texture, int rows, int columns)
        {
            Texture = texture;
            Rows = rows;
            Columns = columns;
            Index = 0;
        }
    }
}