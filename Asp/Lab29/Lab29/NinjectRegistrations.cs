using JsonHelper;
using Ninject.Modules;

namespace Lab29
{
    public class NinjectRegistrations : NinjectModule
    {
        public override void Load()
        {
            Bind<IPhoneDictionary>().To<RecordRepository>();
        }
    }
}