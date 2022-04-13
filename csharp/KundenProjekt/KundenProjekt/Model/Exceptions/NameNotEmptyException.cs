using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace KundenProjekt.Model
{
    public class NameNotEmptyException : Exception
    {
        public NameNotEmptyException(string? message) : base(message)
        {
        }
    }
}
