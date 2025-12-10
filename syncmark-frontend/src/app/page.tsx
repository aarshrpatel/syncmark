export default function Home() {
  return (
    <div className="min-h-screen bg-gray-50 dark:bg-gray-900">
      <main className="max-w-4xl mx-auto px-4 py-8">
        <h1 className="text-4xl font-bold text-gray-900 dark:text-white mb-4">
          SyncMark
        </h1>
        <p className="text-lg text-gray-600 dark:text-gray-300 mb-8">
          Real-time collaborative document editing
        </p>
        <div className="bg-white dark:bg-gray-800 rounded-lg shadow p-6">
          <p className="text-gray-700 dark:text-gray-200">
            Editor will load here...
          </p>
        </div>
      </main>
    </div>
  );
}
