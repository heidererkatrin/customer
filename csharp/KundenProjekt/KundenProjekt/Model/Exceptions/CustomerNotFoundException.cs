using System.Runtime.Serialization;

namespace KundenProjekt.Model
{
    [Serializable]
    internal class CustomerNotFoundException : Exception
    {
        public CustomerNotFoundException(string firstName, string lastName) : base("No customer with name: " + firstName
            + " " + lastName + " found!")
        {
        }

    }
}