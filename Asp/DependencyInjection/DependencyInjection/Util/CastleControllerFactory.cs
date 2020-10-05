using Castle.Windsor;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Routing;

namespace DependencyInjection.Util
{
    public class CastleControllerFactory:DefaultControllerFactory
    {
        //Контейнер
        public IWindsorContainer Container { get; protected set; }

        public CastleControllerFactory(IWindsorContainer container)
        {
            if (container == null)
                throw new ArgumentNullException("container");
            Container = container;
        }

        //Получение контроллера для обработки запроса
        protected override IController GetControllerInstance(RequestContext requestContext, Type controllerType)
        {
            if (controllerType == null)
                return null;
            //Получем запрошенный контроллер от Castle
            return Container.Resolve(controllerType) as IController;
        }

        //Освобождаем контроллер
        public override void ReleaseController(IController controller)
        {
            var disposableController = controller as IDisposable;
            if (disposableController != null)
                disposableController.Dispose();
            Container.Release(controller);
        }
    }
}