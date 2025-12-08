import tkinter as tk
from view.video_calls_view import VideoCallsView


def main():
    root = tk.Tk()
    root.title("Video Calls App")

    app = VideoCallsView(root)
    app.pack(padx=20, pady=20)

    root.mainloop()


if __name__ == "__main__":
    main()
