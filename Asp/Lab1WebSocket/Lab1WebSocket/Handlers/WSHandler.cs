using System;
using System.Net.WebSockets;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Web;
using System.Web.WebSockets;

namespace Lab1WebSocket.Handlers
{
    public class WSHandler : IHttpHandler
    {
        public bool IsReusable
        {
            get { return true; }
        }

        public void ProcessRequest(HttpContext context)
        {
            if (context.IsWebSocketRequest)
                context.AcceptWebSocketRequest(WebSocketRequest);
        }

        private async Task WebSocketRequest(AspNetWebSocketContext context)
        {
            WebSocket client = context.WebSocket;
            while (true)
            {
                if (client.State == WebSocketState.Open)
                {
                    byte[] message = Encoding.UTF8.GetBytes($"{DateTime.Now}");
                    ArraySegment<byte> arr = new ArraySegment<byte>(message, 0, message.Length);
                    await client.SendAsync(arr, WebSocketMessageType.Text, true, CancellationToken.None);
                    Thread.Sleep(2000);
                }
            }
        }
    }
}
