package me.ilich.helloworld;

import android.test.ActivityInstrumentationTestCase2;

import me.ilich.juggler.Navigable;
import me.ilich.juggler.change.Add;
import me.ilich.juggler.change.Remove;
import me.ilich.juggler.hello.gui.activities.TestActivity;
import me.ilich.juggler.hello.states.HelloState;
import me.ilich.juggler.hello.states.InfinityState;
import me.ilich.juggler.hello.states.MainState;
import me.ilich.juggler.hello.states.PreviewState;
import me.ilich.juggler.hello.states.SplashState;

public class ActivityTest extends ActivityInstrumentationTestCase2<TestActivity> {

    private Navigable navigable;

    public ActivityTest() {
        super(TestActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(true);
        navigable = getActivity().navigateTo();
    }

    private void doNavigateToState(int expectedStackSize, Runnable... runnables) {
        for (Runnable runnable : runnables) {
            getInstrumentation().runOnMainSync(runnable);
            getInstrumentation().waitForIdleSync();
        }
        assertEquals(expectedStackSize, getActivity().getSupportFragmentManager().getBackStackEntryCount());
        assertEquals(expectedStackSize, getActivity().getJuggler().getStackLength());
    }

    public void testEmpty() {
        doNavigateToState(0, new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    public void testOneStateDeeper() {
        doNavigateToState(1, new Runnable() {
            @Override
            public void run() {
                navigable.state(Add.deeper(new HelloState()));
            }
        });
    }

    public void testOneStateLinear() {
        doNavigateToState(1, new Runnable() {
            @Override
            public void run() {
                navigable.state(Add.linear(new HelloState()));
            }
        });
    }

    public void testOneStateDeeperDeeper() {
        doNavigateToState(2, new Runnable() {
            @Override
            public void run() {
                navigable.state(Add.deeper(new HelloState()));
            }
        }, new Runnable() {
            @Override
            public void run() {
                navigable.state(Add.deeper(new SplashState()));
            }
        });
    }

    public void testOneStateLinearLinear() {
        doNavigateToState(2, new Runnable() {
            @Override
            public void run() {
                navigable.state(Add.linear(new HelloState()));
            }
        }, new Runnable() {
            @Override
            public void run() {
                navigable.state(Add.linear(new SplashState()));
            }
        });
    }

    public void testOneStateLinearLinearLinearBack() {
        doNavigateToState(2, new Runnable() {
            @Override
            public void run() {
                navigable.state(Add.linear(new InfinityState(1)));
            }
        }, new Runnable() {
            @Override
            public void run() {
                navigable.state(Add.linear(new InfinityState(2)));
            }
        }, new Runnable() {
            @Override
            public void run() {
                navigable.state(Add.linear(new InfinityState(3)));
            }
        }, new Runnable() {
            @Override
            public void run() {
                navigable.backState();
            }
        });
    }

    public void testOneStateLinearDeeperLinearLinearUp() {
        doNavigateToState(1, new Runnable() {
            @Override
            public void run() {
                navigable.state(Add.linear(new InfinityState(1)));
            }
        }, new Runnable() {
            @Override
            public void run() {
                navigable.state(Add.deeper(new InfinityState(2)));
            }
        }, new Runnable() {
            @Override
            public void run() {
                navigable.state(Add.linear(new InfinityState(3)));
            }
        }, new Runnable() {
            @Override
            public void run() {
                navigable.state(Add.linear(new InfinityState(3)));
            }
        }, new Runnable() {
            @Override
            public void run() {
                navigable.upState();
            }
        });
    }

    public void testOneStateLinearDeeperLinearLinearBack() {
        doNavigateToState(3, new Runnable() {
            @Override
            public void run() {
                navigable.state(Add.linear(new InfinityState(1)));
            }
        }, new Runnable() {
            @Override
            public void run() {
                navigable.state(Add.deeper(new InfinityState(2)));
            }
        }, new Runnable() {
            @Override
            public void run() {
                navigable.state(Add.linear(new InfinityState(3)));
            }
        }, new Runnable() {
            @Override
            public void run() {
                navigable.state(Add.linear(new InfinityState(3)));
            }
        }, new Runnable() {
            @Override
            public void run() {
                navigable.backState();
            }
        });
    }

    public void testRemoveLast() {
        doNavigateToState(1, new Runnable() {
            @Override
            public void run() {
                navigable.state(Add.linear(new InfinityState(1)));
            }
        }, new Runnable() {
            @Override
            public void run() {
                navigable.state(Remove.last(), Add.linear(new InfinityState(2)));
            }
        });
    }

    public void testRemoveLast2() {
        doNavigateToState(2, new Runnable() {
            @Override
            public void run() {
                navigable.state(Add.linear(new MainState()));
            }
        }, new Runnable() {
            @Override
            public void run() {
                navigable.state(Add.deeper(new PreviewState()));
            }
        }, new Runnable() {
            @Override
            public void run() {
                navigable.state(Remove.last(), Add.deeper(new InfinityState(3)));
                //navigable.state(Remove.last());
            }
        });
    }

    public void testDig() {
        doNavigateToState(2, new Runnable() {
            @Override
            public void run() {
                navigable.state(Add.deeper(new MainState(), "A"));
            }
        }, new Runnable() {
            @Override
            public void run() {
                navigable.state(Add.deeper(new InfinityState(2)));
            }
        }, new Runnable() {
            @Override
            public void run() {
                navigable.state(Remove.dig("A"), Add.deeper(new InfinityState(1)));
            }
        });
    }

}
