import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Server {
	private ServerSocketChannel serverSocketChannel;
	private Selector selector;
	private ByteBuffer byteBuffer;
	private int remoteClientNum;

	public Server(int port) {
		try {
			initChannel(port);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initChannel(int port) throws Exception {
		serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.bind(new InetSocketAddress(port));
		selector = Selector.open();
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		byteBuffer = ByteBuffer.allocate(1024);

	}

	private void listener() throws IOException {
		while (true) {
			int n = selector.select();
			if (n != 0) {
				Iterator<SelectionKey> iteration = selector.selectedKeys().iterator();
				while (iteration.hasNext()) {
					SelectionKey key = iteration.next();
					if (key.isAcceptable()) {
						ServerSocketChannel server = (ServerSocketChannel) key.channel();
						SocketChannel channel = server.accept();
						registerChannel(selector, channel, SelectionKey.OP_READ);
						remoteClientNum++;
						write(channel, "hhhh".getBytes());
					}
					if (key.isReadable()) {
						read(key);
					}
					iteration.remove();
				}
			}
		}
	}

	private void read(SelectionKey key) {
		// TODO Auto-generated method stub

	}

	private void write(SocketChannel channel, byte[] bytes) {
		// TODO Auto-generated method stub

	}

	private void registerChannel(Selector selector2, SocketChannel channel, int opRead) {
		// TODO Auto-generated method stub

	}
}
